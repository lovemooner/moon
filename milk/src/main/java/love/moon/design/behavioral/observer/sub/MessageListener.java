package love.moon.design.behavioral.observer.sub;

/**
 * Created by ndong on 2017/5/6.
 */
public interface MessageListener {
    public void update();
    public void setSubject(Producer publisher);
}