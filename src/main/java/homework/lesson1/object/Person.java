package homework.lesson1.object;

public class Person implements ActionsObject {
    private long maxrun;
    private long maxjump;

    public Person() {
    }

    public Person(long maxrun, long maxjump) {
        this.maxjump = maxjump;
        this.maxrun = maxrun;
    }


    @Override
    public boolean run(long distance) {
        System.out.println("Человек бежит! Дистанция : " + distance);
        if (distance <= maxrun) {
            System.out.println("Человек пробежал! Дистанцию : " + distance);
            return true;
        } else {
            System.out.println("Человек не пробежал! Дистанцию : " + distance);
            return false;
        }
    }

    @Override
    public boolean jump(long height) {
        System.out.println("Человек прыгает! Высота : " + height);
        if (height <= maxjump) {
            System.out.println("Человек перепрыгнул! Высоту : " + height);
            return true;
        } else {
            System.out.println("Человек не перепрыгнул! Высоту : " + height);
            return false;
        }
    }
}
