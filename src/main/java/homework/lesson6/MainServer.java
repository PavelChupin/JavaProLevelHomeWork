package homework.lesson6;

import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {

    public static void main(String[] args) throws InterruptedException {
        //Вторая обобщенная реализация
        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            System.out.println("Сервер запущен");

            SendMessage sendMessage = new SendMessage();

            //Ожидаем подключения от пользователя
            sendMessage.setSocket(serverSocket.accept());

            System.out.println("Клиент подключился");
            sendMessage.start("Вы подключены к чату");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Первая реализация
        /*Socket socket = new Socket();
        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            System.out.println("Сервер запущен");

            //Ожидаем подключения от пользователя
            socket = serverSocket.accept();

            System.out.println("Клиент подключился");

            //Как только получили подключение, устанавливаем входящий и исходящий поток данных с клиентом
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Thread threadIn = new Thread(new InputMessage(in));
            Thread threadOut = new Thread(new OutputMessage(out));
            threadIn.start();
            threadOut.start();

            do{
                Thread.sleep(1);
            }while (threadIn.isAlive() || threadOut.isAlive());

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
