package love.moon.design.observer.simple;

/**
 * Created by ndong on 2017/5/6.
 */
public class Watcher implements java.util.Observer {
    private String name;

    public Watcher(String name){
        this.name=name;
    }

    public void update(java.util.Observable obj, Object arg) {
        System.out.println("Watcher "+name+", count is "
                + ((Integer) arg).intValue());
    }
}
