package love.moon.hibernate;

import org.hibernate.*;
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
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session1 = sessionFactory.openSession();
        Transaction tx1 = session1.beginTransaction();
        CaUser user1 = (CaUser) session1.get(CaUser.class, 1000L);
        System.out.println("  name:" + user1.getUserName() + " version: " + user1.getVersion());

        //开启事务tx2
        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.beginTransaction();
        CaUser user2 = (CaUser) session1.get(CaUser.class, 1000L);         //获取id为1的用户

        user1.setUserName("name3");
        user2.setUserName("name4");

        tx1.commit();
        user1 = (CaUser) session1.get(CaUser.class, 1000L);
        System.out.println("  name:" + user1.getUserName() + " version: " + user1.getVersion());
        tx2.commit();

    }

    @Test
    public void findWithOptimisticLock() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session1 = sessionFactory.openSession();
        //开启事务tx1
        Transaction tx1 = session1.beginTransaction();
        CaUser user1 = (CaUser) session1.get(CaUser.class, 1000L);
        System.out.println("  name:" + user1.getUserName() + " version: " + user1.getVersion());

        //开启事务tx2
        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.beginTransaction();
        CaUser user2 = (CaUser) session1.get(CaUser.class, 1000L);         //获取id为1的用户

        user1.setUserName("name3" + System.currentTimeMillis());
        user2.setUserName("name4" + System.currentTimeMillis());

        session1.update(user1);
        session2.update(user2);
        tx1.commit();
        user1 = (CaUser) session1.get(CaUser.class, 1000L);
        System.out.println("  name:" + user1.getUserName() + " version: " + user1.getVersion());
        tx2.commit();             //..........2

    }

    @Test
    public void findWithPessimisticLock() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session1 = sessionFactory.openSession();
        Transaction tx1 = session1.beginTransaction();
        CaUser user1 = (CaUser) session1.load(CaUser.class, 1000L, LockMode.UPGRADE);
        System.out.println("  name:" + user1.getUserName() + " version: " + user1.getVersion());

        //开启事务tx2
        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.beginTransaction();
        CaUser user2 = (CaUser) session1.get(CaUser.class, 1000L, LockMode.READ);

        user1.setUserName("name3" + System.currentTimeMillis());
        user2.setUserName("name4" + System.currentTimeMillis());

        session1.update(user1);
        session2.update(user2);
        tx1.commit();
        user1 = (CaUser) session1.get(CaUser.class, 1000L);
        System.out.println("  name:" + user1.getUserName() + " version: " + user1.getVersion());
        tx2.commit();             //..........2

    }

    @Test
    public void Load() {
        final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //开启事务trx2
        final Session session1 = sessionFactory.openSession();
//        Transaction tx1 = session1.beginTransaction();
        CaUser user1 = (CaUser) session1.load(CaUser.class, 1000L);
        CaUser user11 = (CaUser) session1.load(CaUser.class, 1000L, LockMode.READ);
        System.out.println("  name:" + user1.getUserName() + " version: " + user1.getVersion());

        //开启事务trx2
        Session session2 = sessionFactory.openSession();
        CaUser user2 = (CaUser) session2.get(CaUser.class, 1000L);
        System.out.println("  name:" + user1.getUserName() + " version: " + user1.getVersion());


    }

    @Test
    public void findWithPessimisticLock2() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session1 = sessionFactory.openSession();
        Transaction tx1 = session1.beginTransaction();
        String hqlStr = "from CaUser as user where user.id=1000";
        Query query1 = session1.createQuery(hqlStr);
        CaUser user1 = (CaUser) query1.list().get(0);
        System.out.println("  name:" + user1.getUserName() + " version: " + user1.getVersion());
        //开启事务tx2
        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.beginTransaction();
        Query query2 = session2.createQuery(hqlStr);
        CaUser user2 = (CaUser) query1.list().get(0);

        user1.setUserName("name3" + System.currentTimeMillis());
        user2.setUserName("name4" + System.currentTimeMillis());

        session1.update(user1);
        session2.update(user2);
        tx1.commit();
        tx2.commit();

    }

    //    @Test
    public void add() {
        Configuration config = new AnnotationConfiguration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//
//        User user1 = new User();
//        user1.setUserName("计算机科学与技术");
//        session.save(user1);
//        session.getTransaction().commit();
    }


}
