package love.moon.design.observer.sub;


/**
 * Created by ndong on 2017/5/6.
 */
public interface Producer {
    //注册一个observer
    void register(MessageListener observer);

    //移除一个observer
    void remove(MessageListener observer);

    //通知所有观察者
    void notifyObservers();

    //获取主题类要发布的消息
    String getMessage();

    void publish(String message);
}