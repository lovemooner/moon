package love.moon.spring.dao;

import love.moon.spring.model.Cart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Author: lovemooner
 * Date: 2017/6/14 9:45
 */
@Repository
public class CartDAO extends BaseDao<Cart> {
    public CartDAO() {
        super(Cart.class);
    }

    public SessionFactory getSessionFactory(){
     return sessionFactory;
    }
}
