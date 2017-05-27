package love.moon.spring.service;

import love.moon.spring.dao.ShoppingCartDao;
import love.moon.spring.dao.ShoppingCartItemDao;
import love.moon.spring.model.Cart;
import love.moon.spring.model.CartItem;
import love.moon.util.JsonUtil;
import love.moon.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Author: lovemooner
 * Date: 2017/5/27 15:13
 */
@Component
public class ShoppingCartServiceImp  implements ShoppingCartService  {
    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartServiceImp.class);
    @Autowired
    private ShoppingCartDao cartDao;
 @Autowired
    private ShoppingCartItemDao cartItemDao;

    @Override
    public List<Cart> getCarts() {
        List<Cart> students = cartDao.getCarts();
        return students;
    }


    private static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String[] yes = {"YES", "NO"};
    Random random = new Random();

    private String randomChars(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(random.nextInt(52)));
        }
        return sb.toString();
    }

    @Override
    public void initData() {
        for (int i = 0; i < 100; i++) {
            Cart cart = new Cart();
            cart.setStatus(CartStatus.toArray()[random.nextInt(4)]);
            cart.setDiscount(yes[random.nextInt(2)]);
            cart.setSummary(randomChars(15));
            cartDao.save(cart);
            CartItem item=new CartItem();
            item.setCartId(cart.getId());
            item.setItemAmount(random.nextInt(10)+1);
            item.setItemSum(NumberUtil.doubleVal(random.nextInt(10)+1));
            cartItemDao.save(item);
        }
        List<Cart> carts = getCarts();
        System.out.println(carts.size());
    }

}
