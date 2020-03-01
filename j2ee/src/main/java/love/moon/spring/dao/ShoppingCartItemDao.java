package love.moon.spring.dao;

import love.moon.spring.model.Cart;
import love.moon.spring.model.CartItem;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/27 17:39
 */
@Repository
public class ShoppingCartItemDao extends BaseDao<CartItem> {

    public ShoppingCartItemDao() {
        super(ShoppingCartItemDao.class);
    }

    public List<CartItem> getCartItems() {
        Query query = sessionFactory.getCurrentSession().createQuery("from CartItem");
        return query.list();
    }

}