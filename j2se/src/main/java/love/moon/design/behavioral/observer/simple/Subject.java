package love.moon.design.behavioral.observer.simple;

import java.util.Observer;
import java.util.Vector;

/**
 * 被观察者
 * @auther lovemooner
 * @date 2019/11/23 21:38
 * @describe
 */
public class Subject {
    private Vector<Observer> obs;

    public Subject() {
        obs = new Vector<>();
    }

    public  void attach(Observer o) {
            obs.addElement(o);
    }

    public  void detach(Observer o) {
        obs.remove(o);
    }
}
