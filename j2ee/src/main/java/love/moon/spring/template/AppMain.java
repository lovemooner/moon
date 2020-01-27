package love.moon.spring.template;

import love.moon.spring.model.User;
import love.moon.spring.template.service.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring\\jdbc-template.xml"});
        Service serviceA = (Service) context.getBean("service");
        User user= serviceA.getUser(1000l);
        System.out.println(user.getUserName());
    }
}
