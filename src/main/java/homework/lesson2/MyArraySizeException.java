package homework.lesson2;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException{


    public MyArraySizeException(){
        super("Размер массива не соответсвует допустимому значению");
    }

}
