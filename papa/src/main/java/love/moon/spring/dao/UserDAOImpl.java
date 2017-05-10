package love.moon.spring.dao;

import love.moon.spring.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public int save(User u) {
        return (Integer) sessionFactory.getCurrentSession().save(u);
    }

    public List<User> findAll() {
//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        Query query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.list();
    }

    public List<User> findAll2() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        return criteria.list();
    }

    public List<User> findAll3() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from user").addEntity(User.class);
        return query.list();
    }
}