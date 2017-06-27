package love.moon.spring.dao;

import love.moon.spring.model.Cart;
import love.moon.spring.model.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Cart> getCarts(int start, int limit) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Cart")
                .setFirstResult(start).setMaxResults(limit);
        return query.list();
    }
}
