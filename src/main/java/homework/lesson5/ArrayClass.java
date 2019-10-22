package homework.lesson5;

import homework.lesson5.variant2.ArrayClassV2;

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
            a[i] = (float) (a[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private void arrayA1(int index, boolean sync) {
        if (sync) {
            synchronized (this) {
                this.a1 = arrayA(this.a1, index);
            }
        } else {
            this.a1 = arrayA(this.a1, index);
        }
    }

    private void arrayA2(int index, boolean sync) {
        if (sync) {
            synchronized (this) {
                this.a2 = arrayA(this.a2, index);
            }
        } else {
            this.a2 = arrayA(this.a2, index);
        }
    }

    private float[] arrayA(float[] f, int index) {
        float[] d = new float[f.length];
        for (int i = 0; i < f.length; i++, index++) {
            d[i] = (float) (f[i] * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
        }
        return d;
    }

    private float[] arrayHalf(int indexFrom, int lenght) {
        float[] f = new float[lenght];
        System.arraycopy(arr, indexFrom, f, 0, lenght);
        return f;
    }

    private void initArrayHalf() {
        this.a1 = arrayHalf(0, HALF);
        this.a2 = arrayHalf(HALF, HALF);
    }

    private float[] arrayJoin(float[] a1, float[] a2) {
        float[] f = new float[SIZE];
        System.arraycopy(a1, 0, f, 0, HALF);
        System.arraycopy(a2, 0, f, HALF, HALF);
        return f;
    }

    private void checkAlive(Thread t1, Thread t2) throws InterruptedException {
        do {
            Thread.sleep(1);
        } while (t1.isAlive() || t2.isAlive());
    }

    private float codeMethod(boolean b) throws InterruptedException {
        long a = System.currentTimeMillis();

        initArrayHalf();

        Thread t1 = new Thread(() -> arrayA1(0, b));
        Thread t2 = new Thread(() -> arrayA2(HALF, b));
        t1.start();
        t2.start();

        //Делаем задержку, ждем пока все потоки закончат работать
        checkAlive(t1, t2);

        //Склеиваем массив
        arrayJoin(this.a1, this.a2);

        return System.currentTimeMillis() - a;
    }

    public float method1() {
        //Создаем массив
        float[] array = new float[SIZE];
        System.arraycopy(arr, 0, array, 0, SIZE);
        long a = System.currentTimeMillis();
        array(array);
        return System.currentTimeMillis() - a;
    }


    public float method2() throws InterruptedException {
        return codeMethod(false);
    }


    public float method3() throws InterruptedException {
        return codeMethod(true);
    }

    public float method4() throws InterruptedException {
        long a = System.currentTimeMillis();

        initArrayHalf();

        ArrayClassV2 ac1 = new ArrayClassV2(this.a1, 0);
        ArrayClassV2 ac2 = new ArrayClassV2(this.a2, HALF);

        Thread t1 = new Thread(ac1, "Поток 1");
        Thread t2 = new Thread(ac2, "Поток 2");
        t1.start();
        t2.start();

        //Делаем задержку, ждем пока все потоки закончат работать
        checkAlive(t1, t2);

        arrayJoin(ac1.getA(), ac2.getA());

        return System.currentTimeMillis() - a;
    }



}

