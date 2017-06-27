package love.moon.listener;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: lovemooner
 * Date: 2017/6/27 17:37
 */
public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring\\spring-simple.xml");
        context.refresh();//触发ContextRefreshedEvent事件
        context.start(); //触发ContextStartedEvent事件
        context.stop();  //触发ContextStoppedEvent事件
        context.close(); //关闭容器，触发ContextClosedEvent事件

    }
}
