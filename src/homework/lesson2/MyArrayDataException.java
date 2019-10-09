package homework.lesson2;

public class MyArrayDataException extends NumberFormatException{

    public MyArrayDataException(int y, int x, String r){
        super("Ячейка массива: Y = " + y + ", X = " + x + ". Содержит значение с не числовыми символами или имеется переполнение типа данных: " + r + ".");

    }


}
