package homework.lesson3;

public class MainHomeWorkLesson3_Task1 {
    public static void main(String[] args) {
        String[] arrays = new String[10];
        arrays[0] = "Two";
        arrays[1] = "ret1";
        arrays[2] = "Three";
        arrays[3] = "ret2";
        arrays[4] = "ret3";
        arrays[5] = "Two";
        arrays[6] = "Three";
        arrays[7] = "ret4";
        arrays[8] = "Three";
        arrays[9] = "ret5";

        Words words = new Words(arrays);

        //Получаем уникальные
        words.uniq().forEach((s)->{
            System.out.println(s);
        });

        words.countWords().forEach((r,t)->{
            System.out.println("Слово " + r + " встречается в массиве " + t + " раз");
        });
        System.out.println();
        words.countWords(1).forEach((r,t)->{
            System.out.println("Слово " + r + " встречается в массиве " + t + " раз");
        });
        System.out.println();
        words.countWords(1,1).forEach((r,t)->{
            System.out.println("Слово " + r + " встречается в массиве " + t + " раз");
        });
    }
}
