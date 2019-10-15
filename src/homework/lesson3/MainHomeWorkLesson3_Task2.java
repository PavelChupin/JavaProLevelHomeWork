package homework.lesson3;

public class MainHomeWorkLesson3_Task2 {
    public static void main(String[] args) {
        //Добавляем данные
        try {
            //TelephoneList telephoneList = new TelephoneList();
            IPhoneBook telephoneList = new TelephoneList("+79137382899", "Chupin");
            telephoneList.add("+79994648595", "Chupin");
            System.out.println(String.format("Phones Chupin: %s", telephoneList.get("Chupin")));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }


    }
}
