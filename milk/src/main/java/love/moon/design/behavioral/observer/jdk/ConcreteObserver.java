package love.moon.design.behavioral.observer.jdk;

/**
 * Created by ndong on 2017/5/6.
 */
public class ConcreteObserver implements java.util.Observer {
    private String name;

    public ConcreteObserver(String name){
        this.name=name;
    }

    public void update(java.util.Observable obj, Object arg) {
        System.out.println("Watcher "+name+", arg is "+ arg);
    }
}
