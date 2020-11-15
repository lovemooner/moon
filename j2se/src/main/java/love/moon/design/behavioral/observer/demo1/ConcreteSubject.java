package love.moon.design.behavioral.observer.demo1;

/**
 * @author : ndong
 * date : 2020/11/4 15:08
 * desc :
 */
public class ConcreteSubject extends Subject{

    private String state;

    public String getState() {
        return state;
    }

    public void notifyObservers(String newState){
        state = newState;
        System.out.println("主题状态为：" + state);
        //状态发生改变，通知各个观察者
        this.notifyObservers(state);
    }

}
