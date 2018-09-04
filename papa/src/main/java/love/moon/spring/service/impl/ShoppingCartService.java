package love.moon.spring.service.impl;

import love.moon.spring.dao.ProductDao;
import love.moon.spring.dao.ShoppingCartDao;
import love.moon.spring.dao.ShoppingCartItemDao;
import love.moon.spring.model.Cart;
import love.moon.spring.model.CartItem;
import love.moon.spring.model.Product;
import love.moon.spring.common.CartStatus;
import love.moon.spring.service.IShoppingCartService;
import love.moon.util.NumberUtil;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author: lovemooner
 * Date: 2017/5/27 15:13
 */
@Component
public class ShoppingCartService implements IShoppingCartService {
    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartService.class);
    @Autowired
    private ShoppingCartDao cartDao;
    @Autowired
    private ShoppingCartItemDao cartItemDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    protected SessionFactory sessionFactory;

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
        int len=100;
        List<Product> products=  productDao.getProducts(0,len);
        List<Long> idList=new ArrayList<Long>();
        for(Product p:products){
            idList.add(p.getId());
        }
        for (int i = 0; i < len; i++) {
            Cart cart = new Cart();
            cart.setStatus(CartStatus.toArray()[random.nextInt(4)]);
            cart.setDiscount(yes[random.nextInt(2)]);
            cart.setSummary(randomChars(15));
            cart.setRemark(randomChars(15));
            cart.setSf(randomChars(10));
            cart.setPf(randomChars(10));
            cartDao.save(cart);
            CartItem item = new CartItem();
            item.setCartId(cart.getId());
            item.setProductId(idList.get(i));
            item.setItemAmount(random.nextInt(10) + 1);
            item.setItemSum(NumberUtil.doubleVal(random.nextInt(10) + 1));
            cartItemDao.save(item);
        }

    }

    public void initProduct(){
            Product product=new Product();
            product.setProductName(randomChars(15));
            product.setInventory(random.nextInt(1000));
            product.setStatus(CartStatus.toArray()[random.nextInt(4)]);
            productDao.save(product);
        }



}
