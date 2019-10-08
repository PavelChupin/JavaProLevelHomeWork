package homework.lesson2;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException{

    public MyArraySizeException(){

    }

    public MyArraySizeException(String s){
        super(s);
    }

}
