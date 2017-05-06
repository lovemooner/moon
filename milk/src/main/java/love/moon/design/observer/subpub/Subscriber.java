package love.moon.design.observer.subpub;


/**
 * Created by ndong on 2017/5/6.
 */
public class Subscriber implements java.util.Observer {
    private String name;

    public Subscriber() {
    }

    public Subscriber(String name) {
        this.name = name;
    }

    public void subscribe(String subjectName) {
        Publisher publisher=Subject.getInstance().getSubject(subjectName);
        publisher.addObserver(this);
    }

    public void update(java.util.Observable obj, Object arg) {`
        System.out.println("Watcher " + name + ", count is "
                + ((Integer) arg).intValue());
    }
}
