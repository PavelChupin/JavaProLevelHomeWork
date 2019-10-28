package homework.lesson7.server.client;

import homework.lesson7.server.Server;

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

    public ClientHandler(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            //Запускаем поток в котором обрабатываем данные связанные с нашим клиентом
            new Thread(() -> {
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

            }).start();

        } catch (IOException e) {
            throw new RuntimeException("Failed to create client handler", e);
        }
    }

    //Основной метод чата по пересылке сообщений
    private void readMessages() throws IOException {
        while (true) {
            String clientMessage = in.readUTF();
            System.out.printf("Message '%s' from client %s%n", clientMessage, clientName);
            if (clientMessage.equals("/end")) {
                return;
            }

            //Если сообщение отправляется только одному пользователю, то отправим сообщение ему лично
            if (clientMessage.startsWith("/w")) {
                sendMessageToPrivate(clientMessage);
            } else {
                //Отправляем сообщение всем пользователям
                server.broadcastMessage(clientName + " : " + clientMessage);
            }
        }
    }

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
        }else{
            sendMessage(String.format("Пользователь Nick = %s, не подключен к серверу",nickName));
        }
    }

    private void closeConnection() {
        server.unSubscribe(this);
        server.broadcastMessage(clientName + " is offline");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Failed to close socket!");
            e.printStackTrace();
        }
    }

    // "/auth login password"
    private void authentication() throws IOException {
        String clientMessage = in.readUTF();
        if (clientMessage.startsWith("/auth")) {
            //split поиск по регулярному выражению частей строки, возврашает массив слов
            //\\s+ разбор строки с учетом множесвенности строки
            String[] loginAndPasswords = clientMessage.split("\\s+");
            String login = loginAndPasswords[1];
            String password = loginAndPasswords[2];

            //Проверка а есть ли такой пользователь у нас в базе пользователей
            String nick = server.getAuthService().getNickByLoginPass(login, password);
            if (nick == null) {
                sendMessage("Неверный логин/пароль");
                return;
            }

            if (server.isNickBusy(nick)) {
                sendMessage("Учетная запись уже используется");
                return;
            }

            sendMessage("/authok " + nick);
            clientName = nick;

            //Сообщаем всем клиентам, что новый клиент подключился
            server.broadcastMessage(clientName + " is online");

            //Подписываемся у сервера что нас нужно обрабатывать
            server.subscribe(this);

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
