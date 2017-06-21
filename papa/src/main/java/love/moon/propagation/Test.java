package love.moon.propagation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * http://ahujyy.iteye.com/blog/1544304
 *
 * Author: lovemooner
 * Date: 2017/6/21 10:11
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring\\jdbc-template.xml"});
        InvokeService ins = (InvokeService) context.getBean("invokeService");
        ins.jdbcInvokeTest();
        System.out.println("test invoke end");
    }
}
