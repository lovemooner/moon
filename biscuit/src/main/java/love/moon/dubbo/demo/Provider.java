package love.moon.dubbo.demo;

import love.moon.dubbo.demo.service.DemoService;
import love.moon.dubbo.demo.service.DemoServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lovemooner on 2017/5/2.
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
        context.start();

        System.in.read(); // 按任意键退出
    }
}
