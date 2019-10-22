package homework.lesson5.variant2;

public class ArrayClass implements Runnable{
    private float[] arr;
    private float[] a;
    private int index;

    public ArrayClass(float[] arr, int index){
        this.arr = arr;
        this.index = index;
        this.a = new float[arr.length/2];
    }

    @Override
    public void run() {
        System.arraycopy(arr,index,a,0,a.length);

        for (int i = 0; i < a.length; i++, index++) {
            a[i] = (float) (a[i] * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
        }
    }

    public float[] getA() {
        return a;
    }
}
