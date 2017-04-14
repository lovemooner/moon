package love.celery.activemq.transport.failover;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午4:17
 */
public class XMessageListener {
    private String user = ActiveMQConnection.DEFAULT_USER;     //默认 用户
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;    //默认 密码

    private Connection connection = null;
    private Session session = null;

    private String url = ActiveMQConnection.DEFAULT_BROKER_URL; //默认的是localhost:8080

    public void init() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                user, password, url);
        connection = connectionFactory.createConnection();
        ((ActiveMQConnection) connection).addTransportListener(new ClientTransportListener());
        //false 参数表示 为非事务型消息，后面的参数表示消息的确认类型（见4.消息发出去后的确认模式）
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
}
