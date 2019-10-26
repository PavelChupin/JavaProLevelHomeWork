package homework.lesson6;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Вторая обобщенная реализация
        SendMessage sendMessage = new SendMessage("localhost", 8089,false);
        sendMessage.start();

        //Первая реализация
        /*Socket socket = new Socket("localhost",8089);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Thread threadIn = new Thread(new InputMessage(in));
        Thread threadOut = new Thread(new OutputMessage(out));
        threadIn.start();
        threadOut.start();

        do{
            Thread.sleep(1);
        }while (threadIn.isAlive() || threadOut.isAlive());
        */
    }
}
