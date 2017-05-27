package love.moon.spring;

import love.moon.spring.model.Cart;
import love.moon.spring.service.ShoppingCartService;
import love.moon.spring.service.ShoppingCartServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/9 11:13
 */
public class App {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring\\infrastructure.xml","spring\\applicationContext.xml"});

        ShoppingCartService cartService = (ShoppingCartServiceImp) context.getBean("shoppingCartServiceImp");
        List<Cart> carts = cartService.getCarts();
        System.out.println(carts.size());
        System.in.read();
    }
}
