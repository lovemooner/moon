package love.moon.design.observer;


/**
 * Created by ndong on 2017/5/6.
 */
public interface Publisher {
    //注册一个observer
    public void register(Observer observer);
    //移除一个observer
    public void remove(Observer observer);
    //通知所有观察者
    public void notifyObservers();
    //获取主题类要发布的消息
    public String getMessage();
}