package love.moon.design.behavioral.observer.subpub;

/**
 * Created by ndong on 2017/5/6.
 */
public interface Observer {
    public void update(MySubject subject, Object arg);
}