package love.moon.design.behavioral.observer.jdk;

/**
 * @auther lovemooner
 * @date 2019/11/23 21:19
 * @describe
 */
public class Demo {

    public static void main(String[] args) {
        Subject subject = new Subject();//受查者
        ConcreteObserver observer1 = new ConcreteObserver("watcher1");//观察者
        ConcreteObserver observer2 = new ConcreteObserver("watcher2");//观察者
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.notify("test");
    }
}
