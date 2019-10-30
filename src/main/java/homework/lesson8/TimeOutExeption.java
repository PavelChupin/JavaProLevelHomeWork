package homework.lesson8;

public class TimeOutExeption extends RuntimeException {

    public TimeOutExeption(){
        super("Превышен timeout ожидания логирования");
    }
}
