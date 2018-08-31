package love.moon.spring.service;

import love.moon.spring.common.ServiceException;
import love.moon.spring.controller.CartController;
import love.moon.spring.dao.CartDAO;
import love.moon.spring.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Random;

/**
 * Author: lovemooner
 * Date: 2017/6/14 9:43
 */
@Component
public class CartServiceImp implements CartService {
    private Logger LOG = LoggerFactory.getLogger(CartServiceImp.class);

    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private ProductService productService;

    @Autowired
    private ApplicationContext ctx;

    public List<Cart> getCarts(int start, int limit) {
        return cartDAO.getCarts(start, limit);
    }

    public void updateCart() throws ServiceException {
        System.out.println("updateCart start");
        Cart cart = cartDAO.getById(11763713L);
        Random random = new Random();
        String remark = cart.getRemark();
        cart.setRemark(String.valueOf(random.nextInt(100)));
        System.out.println("Old remark:" + remark + " New remark:" + cart.getRemark());
        cartDAO.update(cart);
        productService.updateProduct();
        System.out.println("updateCart");
    }

    public void updateCart2() throws ServiceException {
        System.out.println("updateCart start");
        Long cartId=11763713L;
        Session session= cartDAO.getSessionFactory().openSession();
        session.beginTransaction();
        Cart cart= (Cart) session.get(Cart.class, cartId);
        Random random = new Random();
        String remark = cart.getRemark();
        cart.setRemark(String.valueOf(random.nextInt(100)));
        System.out.println("Old remark:" + remark + " New remark:" + cart.getRemark());
        session.update(cart);
//        session.getTransaction().commit();
    }

    public void updateCart1() {
        System.out.println("updateCart start");
        HibernateTransactionManager txManager = (HibernateTransactionManager) ctx.getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = txManager.getTransaction(def); // 获得事务状态
        try {
            SessionFactory sessionFactory = cartDAO.getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
            Cart cart = (Cart) session.get(Cart.class, 11763713L);
            Random random = new Random();
            cart.setRemark(String.valueOf(random.nextInt(100)));
            session.update(cart);
            try {
                productService.updateProduct();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
            txManager.commit(status);
        } catch (Exception e) {
            txManager.rollback(status);
            LOG.error(e.getMessage(), e);
        }
    }

    public void updateCartItem() {
        System.out.println("updateCart start");
        HibernateTransactionManager txManager = (HibernateTransactionManager) ctx.getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = txManager.getTransaction(def); // 获得事务状态
        try {
            SessionFactory sessionFactory = cartDAO.getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
            Cart cart = (Cart) session.get(Cart.class, 11763713L);
            Random random = new Random();
            cart.setRemark(String.valueOf(random.nextInt(100)));
            session.update(cart);
            //逻辑代码，可以写上你的逻辑处理代码
            txManager.commit(status);
        } catch (Exception e) {
            txManager.rollback(status);
            LOG.error(e.getMessage(), e);
        }
    }


}
