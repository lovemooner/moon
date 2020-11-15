package love.moon.design.behavioral.observer.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ndong
 * date : 2020/11/4 15:07
 * desc :
 */
public abstract class Subject {
    /**
     * 用来保存注册的观察者对象
     */
    private List<Observer> list = new ArrayList<>();
    /**
     * 注册观察者对象
     * @param observer    观察者对象
     */
    public void addObserver(Observer observer){

        list.add(observer);
        System.out.println("Attached an observer");
    }
    /**
     * 删除观察者对象
     * @param observer    观察者对象
     */
    public void remove(Observer observer){

        list.remove(observer);
    }
    /**
     * 通知所有注册的观察者对象
     */
    public void notifyObservers(String arg){

        for(Observer observer : list){
            observer.update(arg);
        }
    }
}
