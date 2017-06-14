package love.moon.spring.service;

import love.moon.spring.dao.CartDAO;
import love.moon.spring.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Author: lovemooner
 * Date: 2017/6/14 9:43
 */
@Component
public class CartServiceImp implements CartService{

    @Autowired
    private CartDAO cartDAO;

    public void updateCart() {
        System.out.println("updateCart start");
        Cart cart = cartDAO.getById(11763713L);
        Random random = new Random();
        cart.setRemark(String.valueOf(random.nextInt(100)));
        cartDAO.update(cart);
        while (true){
            try {
                Thread.sleep(600000L);
                System.out.println("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void tUpdateCart() {
        System.out.println("updateCart start");
        SessionFactory sessionFactory= cartDAO.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Cart cart = (Cart) session.get(Cart.class, 11763713L);
        Random random = new Random();
        cart.setRemark(String.valueOf(random.nextInt(100)));
        session.update(cart);
    }
}
