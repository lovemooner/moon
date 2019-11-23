package love.moon.design.behavioral.observer.jdk;

/**
 * Created by ndong on 2017/5/6.
 */
public class Subject extends java.util.Observable {

    public void notify(Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}
