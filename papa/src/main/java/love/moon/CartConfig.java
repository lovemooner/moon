package love.moon;

import love.moon.spring.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * Author: lovemooner
 * Date: 2017/5/27 15:33
 */
public class CartConfig {
    private static final Logger LOG = LoggerFactory.getLogger(CartConfig.class);


    private Random random = new Random();


    public void insertData() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring\\infrastructure.xml", "spring\\applicationContext.xml"});
        final ShoppingCartService cartService = (ShoppingCartService) context.getBean("shoppingCartServiceImp");
        cartService.initData();
//        for (int i = 0; i < 20; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < 100000; i++) {
//                        cartService.initProduct();
//                    }
//                }
//            }).start();
//
//        }

        }

    public static void main(String[] args) {
        CartConfig cartConfig = new CartConfig();
        cartConfig.insertData();
    }
}
