package love.moon.design.behavioral.observer.subpub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ndong on 2017/5/6.
 */
public class MySubject implements Subject {
    private List<Observer> observers;
    private boolean changed;
    private String message;
    //对象锁， 用于同步更新观察者列表
    private final Object mutex = new Object();
    public MySubject() {
        observers = new ArrayList<Observer>();
        changed = false;
    }
    @Override
    public void register(Observer observer) {
        if (observer == null)
            throw new NullPointerException();
        //保证不重复
        if (!observers.contains(observer))
            observers.add(observer);
    }
    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object object) {
        // temp list
//        List<Observer> tempObservers = null;
//        synchronized (mutex) {
//            if (!changed)
//                return;
//            tempObservers = new ArrayList<>(this.observers);
//            this.changed = false;
//        }
//        for(Observer obj : tempObservers) {
//            obj.update(this,object);
//        }
    }
    //主题类发布新消息
    public void publish(String message) {
        System.out.println("The Subject make a change: " + message);
        this.message = message;
        this.changed = true;
        notifyObservers(message);
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}