package homework.lesson2;

public class ArraySum {
    public static final int sizeY = 4;
    public static final int sizeX = 4;


    public long arraySum(String[][] strings) throws MyArraySizeException, MyArrayDataException {
        long sum = 0;

        //Проверяем массив на размерность
        if((strings == null) || (strings.length != this.sizeY) || (strings[0].length != this.sizeX)){
            throw new MyArraySizeException("Размер массива не соответсвует допустимому значению");
        }

        //Начинаем обходить массив
        for (int y = 0;y < strings.length;y++){
            for (int x = 0;x< strings[y].length;x++) {
                try{
                sum+=Long.parseLong(strings[y][x]);
                }catch (Exception e){
                    throw new MyArrayDataException("Ячейка массива: Y = " + y + ", X = " + x + ". Содержит значение с не числовыми символами: " + strings[y][x] + ".");
                }

            }
        }

        return sum;
    }

}
