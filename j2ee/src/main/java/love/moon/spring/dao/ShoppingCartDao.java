package love.moon.spring.dao;

import love.moon.spring.model.Cart;
import love.moon.spring.model.Student;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/27 15:14
 */
@Repository
public class ShoppingCartDao extends BaseDao<Cart> {

    public ShoppingCartDao() {
        super(ShoppingCartDao.class);
    }

    public List<Cart> getCarts() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Cart");
        return query.list();
    }

}
