package homework.lesson6.Runnable;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

public class OutputMessage implements Runnable {
    private DataOutputStream out;

    public OutputMessage(DataOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String s = scanner.nextLine();
            try {
                out.writeUTF(s);

                if(s.equals("/end")){
                System.out.println("Закрыто соединение");
                break;
            }

            } catch (SocketException r) {
                System.out.println("Закрыто соединение");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
