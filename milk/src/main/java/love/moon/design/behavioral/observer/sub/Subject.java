package love.moon.design.behavioral.observer.sub;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther dongnan
 * @date 2019/11/23 21:32
 * @describe
 */
public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}