package homework.lesson2;

import java.util.Scanner;

public class MainHomeWorkLesson2 {
    public static final int SIZEX = 4;
    public static final int SIZEY = 4;

    public static void main(String[] args) {

        String[][] strings = new String[SIZEY][SIZEX];
        Scanner scanner = new Scanner(System.in);

        for (int y = 0; y < SIZEY; y++) {
            for (int x = 0; x < SIZEX; x++) {
                strings[y][x] = scanner.nextLine();
            }
        }


        ArraySum arraySum = new ArraySum();

        try {
            System.out.println(arraySum.arraySum(strings));
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }
    }


}
