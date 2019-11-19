package love.moon.design.behavioral.listener;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by ndong on 2017/5/6.
 */
public class EventSource {
    private Vector repository = new Vector();//监听自己的监听器队列

    public void addDemoListener(DemoListener dl) {
        repository.addElement(dl);
    }

    public void notifyDemoEvent() {
        Enumeration elements = repository.elements();
        while (elements.hasMoreElements()) {
            DemoListener dl = (DemoListener) elements.nextElement();
            dl.handleEvent(new DemoEvent(this));
        }
    }

    public static void main(String[] args) {
        EventSource ds = new EventSource();
        //将监听器在事件源对象中登记：
        ds.addDemoListener(event -> System.out.println("Method come from 匿名类..."));
        ds.notifyDemoEvent();//触发事件、通知监听器
    }
}
