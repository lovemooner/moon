package love.moon.design.behavioral.observer.demo1;

/**
 * @author : ndong
 * date : 2020/11/4 15:11
 * desc :
 */
public class Main {

    public static void main(String[] args) {
        //创建主题对象
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(new ConcreteObserver());
        //改变主题对象的状态
        subject.notifyObservers("new state");
    }

}

