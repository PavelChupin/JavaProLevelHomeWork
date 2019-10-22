package homework.lesson5.variant2;

public class MainHomeWorkLesson5_ver2 {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    static float[] arr = new float[SIZE];

    public static void main(String[] args) throws InterruptedException {
        for(int i =0;i < SIZE;i++){
            arr[i] = 1;
        }

        method1();
        method2();
    }

    public static void method1() {
        //Создаем массив
        float[] array = new float[arr.length];
        System.arraycopy(arr,0,array,0,arr.length);
        long a = System.currentTimeMillis();

        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Метод 1 выполнился за " +  (System.currentTimeMillis() - a));
    }

    public static void method2() throws InterruptedException {
        long a = System.currentTimeMillis();
        ArrayClass a1 = new ArrayClass(arr,0);
        ArrayClass a2 = new ArrayClass(arr,HALF);

        Thread t1 = new Thread(a1,"Поток 1");
        Thread t2 = new Thread(a2,"Поток 2");

        t1.start();
        t2.start();

        //Делаем задержку, ждем пока все потоки закончат работать
        do {
            Thread.sleep(1);
        } while (t1.isAlive() || t2.isAlive());

        arrayJoin(a1.getA(),a2.getA());

        System.out.println("Метод 2 выполнился за " +  (System.currentTimeMillis() - a));
    }

    private static float[] arrayJoin(float[] a1, float[] a2) {
        float[] f = new float[arr.length];
        System.arraycopy(a1, 0, f, 0, HALF);
        System.arraycopy(a2, 0, f, HALF, HALF);
        return f;
    }
}
