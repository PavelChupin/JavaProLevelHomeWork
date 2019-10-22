package homework.lesson5;

import java.util.LinkedList;

public class ArrayClass {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    private float[] arr;
    private float[] a1;
    private float[] a2;

    public ArrayClass() {
        this.arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1f;
        }
    }

    private void array(float[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = (float)(a[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private void arrayA1(int index, boolean sync) {
        if(sync){
            synchronized (this) {a1 = arrayA(a1,index);}
        }
        else{
        a1 = arrayA(a1,index);}
    }

    private void arrayA2(int index, boolean sync) {
        if(sync){
            synchronized (this) {a2 = arrayA(a2,index);}
        }
        else{
            a2 = arrayA(a2,index);}
    }

    private float[] arrayA(float[] f,int index) {
        float[] d = new float[f.length];
        for (int i = 0; i < f.length; i++, index++) {
            d[i] = (float) (f[i] * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
        }
        return d;
    }


    private float[] arrayHalf(int indexFrom, int lenght) {
        float[] f = new float[HALF];
        System.arraycopy(arr, indexFrom, f, 0, lenght);
        return f;
    }

    private float[] arrayJoin() {
        float[] f = new float[SIZE];
        System.arraycopy(a1, 0, f, 0, HALF);
        System.arraycopy(a2, 0, f, HALF, HALF);
        return f;
    }

    public float method1() {
        //Создаем массив
        float[] array = new float[arr.length];
        System.arraycopy(arr,0,array,0,arr.length);
        long a = System.currentTimeMillis();
        array(array);
        return System.currentTimeMillis() - a;
    }

    public float method2() throws InterruptedException {
        long a = System.currentTimeMillis();

        this.a1 = arrayHalf(0, HALF);
        this.a2 = arrayHalf(HALF, HALF);

        Thread t1 = new Thread(() -> arrayA1(0,false));
        Thread t2 = new Thread(() -> arrayA2(HALF,false));
        t1.start();
        t2.start();

        //Делаем задержку, ждем пока все потоки закончат работать
        do {
            Thread.sleep(1);
        } while (t1.isAlive() || t2.isAlive());

        //Склеиваем массив
        arrayJoin();

        return System.currentTimeMillis() - a;
    }

    public float method3() throws InterruptedException {
        long a = System.currentTimeMillis();

        this.a1 = arrayHalf(0, HALF);
        this.a2 = arrayHalf(HALF, HALF);

        Thread t1 = new Thread(() -> arrayA1(0,true));
        Thread t2 = new Thread(() -> arrayA2(HALF,true));
        t1.start();
        t2.start();

        //Делаем задержку, ждем пока все потоки закончат работать
        do {
            Thread.sleep(1);
        } while (t1.isAlive() || t2.isAlive());

        //Склеиваем массив
        arrayJoin();

        return System.currentTimeMillis() - a;
    }




}

