package love.celery.activemq.pool;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午4:00
 */

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class TestPool {
    public static final Logger LOG = LoggerFactory.getLogger(TestPool.class);
    private static PooledConnectionFactory poolFactory;

    private static String user = ActiveMQConnection.DEFAULT_USER; //默认 用户
    private static String password = ActiveMQConnection.DEFAULT_PASSWORD;  //默认 密码
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; //默认的是localhost:8080

    /**
     * 获取单例的PooledConnectionFactory
     *
     * @return
     */
    private static synchronized PooledConnectionFactory getPooledConnectionFactory() {
        LOG.info("getPooledConnectionFactory");
        if (poolFactory != null) return poolFactory;
        LOG.info("getPooledConnectionFactory create new");
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user, password, url);
        poolFactory = new PooledConnectionFactory(factory);
        // 池中借出的对象的最大数目
        poolFactory.setMaxConnections(100);
        poolFactory.setMaximumActiveSessionPerConnection(50);
        //后台对象清理时，休眠时间超过了3000毫秒的对象为过期
        poolFactory.setTimeBetweenExpirationCheckMillis(3000);
        LOG.info("getPooledConnectionFactory create success");
        return poolFactory;
    }

    /**
     * 1.对象池管理connection和session,包括创建和关闭等
     * 2.PooledConnectionFactory缺省设置MaxIdle为1，
     * 官方解释Set max idle (not max active) since our connections always idle in the pool.   *
     *
     * @return * @throws JMSException
     */
    public static Session createSession() throws JMSException {
        PooledConnectionFactory poolFactory = getPooledConnectionFactory();
        PooledConnection pooledConnection = (PooledConnection) poolFactory.createConnection();
        //false 参数表示 为非事务型消息，后面的参数表示消息的确认类型（见4.消息发出去后的确认模式）
        return pooledConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public static void produce(String subject, String msg) {
        LOG.info("producer send msg: {} ", msg);
        try {
            Session session = createSession();
            LOG.info("create session");
            TextMessage textMessage = session.createTextMessage(msg);
            Destination destination = session.createQueue(subject);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.send(textMessage);
            LOG.info("create session success");
        } catch (JMSException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        TestPool.produce("test.subject", "hello");
    }
}
