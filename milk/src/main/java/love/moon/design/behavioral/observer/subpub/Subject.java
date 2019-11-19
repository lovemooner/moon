package love.moon.design.behavioral.observer.subpub;


/**
 * Created by ndong on 2017/5/6.
 */
public interface Subject {
    //注册一个observer
    public void register(Observer observer);
    //移除一个observer
    public void remove(Observer observer);
    //通知所有观察者
    public void notifyObservers(Object object);
    //获取主题类要发布的消息
    public String getMessage();
}