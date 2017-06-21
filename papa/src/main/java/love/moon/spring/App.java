package love.moon.spring;

import love.moon.spring.common.ServiceException;
import love.moon.spring.service.CartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/5/9 11:13
 */
public class App {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring\\infrastructure.xml","spring\\applicationContext.xml"});

        CartService cartService = (CartService) context.getBean("cartServiceImp");
        try {
            cartService.updateCart();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.in.read();
    }
}
