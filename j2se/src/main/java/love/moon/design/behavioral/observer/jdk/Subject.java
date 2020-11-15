package love.moon.design.behavioral.observer.jdk;

/**
 * Created by lovemooner on 2017/5/6.
 */
public class Subject extends java.util.Observable {

    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }
}
