package love.moon.spring.dao;

import love.moon.spring.model.Cart;
import love.moon.spring.model.Fund;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2018/9/2 13:40
 */
@Repository
public class FundDao extends BaseDao<Fund> {

    public FundDao() {
        super(Fund.class);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<Fund> getFunds(int start, int limit) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Fund")
                .setFirstResult(start).setMaxResults(limit);
        return query.list();
    }
}
