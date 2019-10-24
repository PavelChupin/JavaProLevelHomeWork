package homework.lesson6.Runnable;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

public class InputMessage implements Runnable {
    private DataInputStream in;

    public InputMessage(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(in.readUTF());
            } catch (SocketException s) {
                System.out.println("Закрыто соединение");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
