package homework.lesson2;

public class ArraySum {
    public static final int SIZEY = 4;
    public static final int SIZEX = 4;


    public int arraySum(String[][] strings) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        //Проверяем массив на размерность вертикале
        if ((strings == null) || (strings.length != this.SIZEY)) {
            throw new MyArraySizeException();
        }//Проверим размер массива по горизонтале
        else {
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].length != SIZEX) {
                    throw new MyArraySizeException();
                }
            }
        }

        //Начинаем обходить массив
        for (int y = 0; y < strings.length; y++) {
            for (int x = 0; x < strings[y].length; x++) {
                try {
                    sum += Integer.parseInt(strings[y][x]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(y, x, strings[y][x]);
                }
            }
        }

        return sum;
    }

}
