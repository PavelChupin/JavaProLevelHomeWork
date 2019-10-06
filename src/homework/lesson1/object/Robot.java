package homework.lesson1.object;

import homework.lesson1.ActionsObject;

public class Robot implements ActionsObject {
    private long maxrun;
    private long maxjump;

    public Robot() {
    }

    public Robot(long maxrun, long maxjump) {
        this.maxjump = maxjump;
        this.maxrun = maxrun;
    }

    @Override
    public boolean run(long distance) {
        System.out.println("Робот бежит! Дистанция : " + distance);
        if (distance <= maxrun) {
            System.out.println("Робот пробежал! Дистанцию : " + distance);
            return true;
        } else {
            System.out.println("Робот не пробежал! Дистанцию : " + distance);
            return false;
        }
    }

    @Override
    public boolean jump(long height) {
        System.out.println("Робот прыгает! Высота : " + height);
        if (height <= maxjump) {
            System.out.println("Робот перепрыгнул! Высоту : " + height);
            return true;
        } else {
            System.out.println("Робот не перепрыгнул! Высоту : " + height);
            return false;
        }
    }
}
