package love.moon.design.observer;

/**
 * Created by ndong on 2017/5/6.
 */
public interface Observer {
    public void update();

    public void setPublisher(Publisher publisher);
}