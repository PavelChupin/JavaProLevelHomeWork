package homework.lesson3;

public class MainHomeWorkLesson3_Task2 {
    public static void main(String[] args) {
        //Добавляем данные
        try {
            //TelephoneList telephoneList = new TelephoneList();
            TelephoneList telephoneList = new TelephoneList("+79137382899", "Chupin");
            telephoneList.add("+79994648595", "Chupin");
            System.out.println("Phones Chupin: " + telephoneList.get("Chupin"));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }


    }
}
