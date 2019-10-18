package homework.lesson2;

public class MyArrayDataException extends NumberFormatException{

    public MyArrayDataException(int y, int x, String r){
        super(String.format("Ячейка массива: Y = %d, X = %d. Содержит значение с не числовыми символами или имеется переполнение типа данных: %s.",y,x,r));

    }


}
