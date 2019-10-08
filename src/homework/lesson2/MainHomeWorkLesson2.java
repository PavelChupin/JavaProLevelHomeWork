package homework.lesson2;

import java.util.Scanner;

public class MainHomeWorkLesson2 {
    public static final int SIZE = 4;

    public static void main(String[] args) {

        String[][] strings = new String[SIZE][SIZE];
        Scanner scanner = new Scanner(System.in);

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
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
