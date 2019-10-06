package homework.lesson1.sportobject;

import homework.lesson1.ActionsObject;
import homework.lesson1.Sports;

public class RunLine implements Sports {
    private long distance;

    public RunLine(){}

    public RunLine(long distance){
        this.distance = distance;
    }

    @Override
    public boolean doAction(ActionsObject actionsObject){
        return actionsObject.run(distance);
    }



}
