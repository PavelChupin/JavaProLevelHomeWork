package homework.lesson8.server.client;

import homework.lesson8.messageconvert.*;
import homework.lesson8.server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;

    private String clientName;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Socket socket, Server server, int timeout) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            //Запускаем поток в котором обрабатываем данные связанные с нашим клиентом
            new Thread(() -> {
                try {
                    Thread chat = new Thread(() -> {
                        try {
                            //Авторизируем
                            authentication();
                            //Начинаем чат между сервером и пользователем
                            readMessages();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            closeConnection();
                        }
                    });
                    chat.start();

                    //Запускаем ожидание
                    waitTimeOut(timeout, chat);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create client handler", e);
        }
    }

    private void waitTimeOut(int timeout, Thread chat) throws InterruptedException {
        Thread waittimeout = new Thread(() -> {
            try {
                long a = System.currentTimeMillis();
                do {
                    Thread.sleep(1);
                } while (System.currentTimeMillis() - a <= timeout * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        waittimeout.setDaemon(true);
        waittimeout.start();

        //Если поток ожидания закончил работу, а пользователь не был авторизирован, то закроем сокет и отправим клиенту сообщение
        do {
            Thread.sleep(1);
        } while (waittimeout.isAlive());

        if (this.clientName == null || this.clientName.isEmpty()) {
            System.out.println("Client disconnect of timeout");
            sendMessage("TimeOut for connection end");
            if (chat.isAlive()) {
                chat.interrupted();
            }
            closeConnection();
        }
    }

    // "/auth login password"
    private void authentication() throws IOException {
        while (true) {
            String clientMessage = in.readUTF();
            Message message = Message.fromJson(clientMessage);
            if (message.command == Command.AUTH_MESSAGE) {
                AuthMessage authMessage = message.authMessage;
                String login = authMessage.login;
                String password = authMessage.pass;

                //Проверка а есть ли такой пользователь у нас в базе пользователей
                String nick = server.getAuthService().getNickByLoginPass(login, password);
                if (nick == null) {
                    sendMessage("Неверный логин/пароль");
                    continue;
                }

                if (server.isNickBusy(nick)) {
                    sendMessage("Учетная запись уже используется");
                    continue;
                }

                sendMessage("/authok " + nick);
                clientName = nick;

                //Сообщаем всем клиентам, что новый клиент подключился
                server.broadcastMessage(clientName + " is online", this);

                //Подписываемся у сервера что нас нужно обрабатывать
                server.subscribe(this);
                break;
            }
        }
    }

    //Основной метод чата по пересылке сообщений
    private void readMessages() throws IOException {
        while (true) {
            String clientMessage = in.readUTF();
            System.out.printf("PrivateMessage '%s' from client %s%n", clientMessage, clientName);
            Message m = Message.fromJson(clientMessage);
            switch (m.command) {
                case PUBLIC_MESSAGE:
                    PublickMessage publickMessage = m.publickMessage;
                    server.broadcastMessage(publickMessage.from + " : " + publickMessage.message, this);
                    break;
                case PRIVATE_MESSAGE:
                    PrivateMessage privateMessage = m.privateMessage;
                    server.messageToPrivateLogin(privateMessage.to, privateMessage.message);
                    break;
                case END:
                    return;
            }
        }
    }
/*
    private void sendMessageToPrivate(String clientMessage) {
        String[] sendTo = clientMessage.split("\\s+");
        String nickName = null;
        String message = null;

        if (sendTo.length < 3) {
            sendMessage("Не корректно указаны параметры для отправки приватного сообщения.");
            return;
        } else {
            nickName = sendTo[1];
            message = clientMessage.replace(sendTo[0], "").replace(sendTo[1], "").trim();
        }

        if (server.isNickBusy(nickName)) {
            server.messageToPrivateLogin(nickName, clientName + " : " + message);
            return;
        } else {
            sendMessage(String.format("Пользователь Nick = %s, не подключен к серверу", nickName));
        }
    }*/

    private void closeConnection() {
        server.unSubscribe(this);
        server.broadcastMessage(clientName + " is offline", this);
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Failed to close socket!");
            e.printStackTrace();
        }
    }


    public void sendMessage(String s) {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            System.err.println("Failed to send message to user " + clientName + " : " + s);
            e.printStackTrace();
        }
    }

    public String getClientName() {
        return clientName;
    }
}
