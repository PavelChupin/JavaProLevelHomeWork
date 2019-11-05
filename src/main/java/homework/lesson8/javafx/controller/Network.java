package homework.lesson8.javafx.controller;

import homework.lesson8.messageconvert.Message;
import javafx.application.Platform;
import homework.lesson8.javafx.controller.message.IMessageService;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network implements Closeable {

    private final String serverAddress;
    private final int port;
    private final IMessageService messageService;

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Network(String serverAddress, int port, IMessageService messageService) throws IOException {
        this.serverAddress = serverAddress;
        this.port = port;
        this.messageService = messageService;
        initNetworkState(serverAddress,port);
    }

    public void initNetworkState(String serverAddress, int port) throws IOException {
        this.socket = new Socket(serverAddress, port);
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());

        Thread readServerThread = new Thread(this::readMessagesFromServer);
        readServerThread.setDaemon(true);
        readServerThread.start();

    }


    public void send(String message) {
        try {
            if (outputStream == null) {
                initNetworkState(serverAddress, port);
            }
            outputStream.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send message: " + message);
        }
    }

    private void readMessagesFromServer() {
        while (true) {
            try {
                String message = inputStream.readUTF();
                Message msg = Message.fromJson(message);
                Platform.runLater(() -> messageService.processRetrievedMessage(msg));
            } catch (Exception e) {
                System.out.println("Соединение с сервером было разорвано!");
                break;
            }
        }
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
