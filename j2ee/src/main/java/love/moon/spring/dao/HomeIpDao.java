package love.moon.spring.dao;

import love.moon.spring.model.Cart;
import love.moon.spring.model.HomeIP;
import love.moon.util.NumberUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeIpDao  {

    @Autowired
    protected SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<HomeIP> getHomeIps(int start, int limit) {
        Query query = getSession().createQuery("from HomeIP order by created desc")
                .setFirstResult(start).setMaxResults(limit);
        return query.list();
    }

    public int countHomeIps() {
        Query query = getSession().createQuery("select count(*) from HomeIP");
        return NumberUtil.intValue(query.uniqueResult());
    }
}
