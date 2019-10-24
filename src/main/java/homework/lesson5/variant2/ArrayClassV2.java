package homework.lesson5.variant2;

public class ArrayClassV2 implements Runnable {
    private float[] a;
    private int index;

    public ArrayClassV2(float[] a, int index) {
        this.index = index;
        this.a = a;
    }

    @Override
    public void run() {
        for (int i = 0; i < a.length; i++, index++) {
            a[i] = (float) (a[i] * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
        }
    }

    public float[] getA() {
        return a;
    }
}
