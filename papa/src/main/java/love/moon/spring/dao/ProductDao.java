package love.moon.spring.dao;

import love.moon.spring.model.CartItem;
import love.moon.spring.model.Product;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/5/28 12:30
 */
@Repository
public class ProductDao extends BaseDao<Product> {

    public ProductDao() {
        super(Product.class);
    }

    public List<Product> getProducts(int start, int limit) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product")
                .setFirstResult(start).setMaxResults(limit);
        return query.list();
    }
}
