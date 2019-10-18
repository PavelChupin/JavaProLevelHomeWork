package homework.lesson1.object;

public class Cat implements ActionsObject {
    private long maxrun;
    private long maxjump;

    public Cat() {
    }

    public Cat(long maxrun, long maxjump) {
        this.maxjump = maxjump;
        this.maxrun = maxrun;
    }

    @Override
    public boolean run(long distance) {
        System.out.println("Кот бежит! Дистанция : " + distance);
        if (distance <= maxrun) {
            System.out.println("Кот пробежал! Дистанцию : " + distance);
            return true;
        } else {
            System.out.println("Кот не пробежал! Дистанцию : " + distance);
            return false;
        }
    }

    @Override
    public boolean jump(long height) {
        System.out.println("Кот прыгает! Высота : " + height);
        if (height <= maxjump) {
            System.out.println("Кот перепрыгнул! Высоту : " + height);
            return true;
        } else {
            System.out.println("Кот не перепрыгнул! Высоту : " + height);
            return false;
        }
    }
}
