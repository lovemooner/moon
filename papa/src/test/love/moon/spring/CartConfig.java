package love.moon.spring;

import love.moon.spring.model.Cart;
import love.moon.spring.model.CartItem;
import love.moon.spring.service.CartStatus;
import love.moon.spring.service.ShoppingCartService;
import love.moon.spring.service.ShoppingCartServiceImp;
import love.moon.util.JsonUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
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
        ShoppingCartService cartService = (ShoppingCartService) context.getBean("shoppingCartServiceImp");
        cartService.initData();
    }

    public static void main(String[] args) {
        CartConfig cartConfig = new CartConfig();
        cartConfig.insertData();
    }
}
