package love.moon.hibernate;

import love.moon.spring.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
//import love.moon.spring.model.User;

/**
 * Author: lovemooner
 * Date: 2017/5/19 17:07
 */
public class HibernateTest {

    @Test
    public void find() {
        Configuration config = new AnnotationConfiguration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, 1000L);
        System.out.println("id: " + user.getId() + "  name:" + user.getUserName());
        session.getTransaction().commit();
    }

    @Test
    public void add() {
        Configuration config = new AnnotationConfiguration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user1 = new User();
        user1.setUserName("计算机科学与技术");
        session.save(user1);
        session.getTransaction().commit();
    }


}
