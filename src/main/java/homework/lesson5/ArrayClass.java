package homework.lesson5;

public class ArrayClass {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    private float[] arr;

    public ArrayClass(){
        this.arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1f;
        }
    }
    /*
    private float[] creatArray() {
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1f;
        }
        return arr;
    }*/

    public float method1() {
        //Создаем массив
        float[] array = this.arr;//creatArray();
        long a = System.currentTimeMillis();
        array(array,0);
        return System.currentTimeMillis() - a;
    }

    public float method2() {
        //Создаем массив
        float[] array = arr;
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        long a = System.currentTimeMillis();
        //Разбиваем массивы на два
        System.arraycopy(array, 0, a1, 0, HALF);
        System.arraycopy(array, HALF, a2, 0, HALF);

        new Thread(() -> array(a1,0));
        new Thread(() -> array(a2,HALF));

        //Склеиваем массиф
        System.arraycopy(a1, 0, array, 0, HALF);
        System.arraycopy(a2, 0, array, HALF, HALF);

        return System.currentTimeMillis() - a;
    }

    public float method3() {
        //Создаем массив
        float[] array = arr;
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        long a = System.currentTimeMillis();
        //Разбиваем массивы на два
        System.arraycopy(array, 0, a1, 0, HALF);
        System.arraycopy(array, HALF, a2, 0, HALF);

        new Thread(() -> arraysync(a1,0));
        new Thread(() -> arraysync(a2, HALF));

        //Склеиваем массиф
        System.arraycopy(a1, 0, array, 0, HALF);
        System.arraycopy(a2, 0, array, HALF, HALF);

        return System.currentTimeMillis() - a;
    }

    private void array(float[] arr, int index) {
        for (int i = 0; i < arr.length; i++, index++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
        }
    }

    private synchronized void arraysync(float[] arr, int index) {
        for (int i = 0; i < arr.length; i++, index++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
        }
    }

}
