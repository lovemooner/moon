package love.moon.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class APITest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void init(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-1");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @After
    public void destroy(){
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
    /**
     * 让缓存中的状态跟数据表中的记录保持一致（与flush相反),会有两个 select
     * 同 hibernate 中 Session 的 refresh 方法.
     */
    @Test
    public void testRefresh(){
        Cart cart = entityManager.find(Cart.class, 1);
        cart = entityManager.find(Cart.class, 1);

        entityManager.refresh(cart);
    }

    /**
     * 运行到该方法并在提交事务前 ，会强制发送update语句，（数据库的数据没有改变，因为没提交事务）
     * 使数据表中的记录跟缓存中对象状态保持一致,刷新缓存
     *
     * 同 hibernate 中 Session 的 flush 方法.
     */
    @Test
    public void testFlush(){
        Cart customer = entityManager.find(Cart.class, 1);
        System.out.println(customer);
        customer.setRemark("AA");
        entityManager.flush();
    }



    /************************************************************/
    /**
     * 类似于 hibernate 中 Session 的 delete 方法. 把对象对应的记录从数据库中移除
     * 但注意: 该方法只能移除 持久化 对象. 而 hibernate 的 delete 方法实际上还可以移除 游离对象.
     */
    @Test
    public void testRemove(){
        Cart cart = entityManager.find(Cart.class, 2);
        entityManager.remove(cart);
    }

    /**
     * 类似于 hibernate 的 save 方法. 使对象由临时状态变为持久化状态.
     * 	和 hibernate 的 save 方法的不同之处: 若对象有 id, 则不能执行 insert 操作, 而会抛出异常.而hibernate可以
     */
    @Test
    public void testPersistence(){
        Cart customer = new Cart();
        //customer.setId(100); //若这里设置 id, 则不能执行 insert 操作, 而会抛出异常.

        entityManager.persist(customer);
        System.out.println(customer.getId());
    }

    /**
     * #find 类似于 hibernate 中 Session 的 get 方法.
     */
    @Test
    public void testFind() {
        //这里是先在控制台上打印sql语句，再打细线，再打结果
        Cart customer = entityManager.find(Cart.class, 1);
        System.out.println("-------------------------------------");
        System.out.println(customer);
    }
}

