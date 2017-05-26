package love.moon.activemq.ptp.syn;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午4:07
 */

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Producer {

    private String user = ActiveMQConnection.DEFAULT_USER; //默认 用户
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;  //默认 密码
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL; //默认的是localhost:8080
    private String subject = "DEMO.LISTENER"; //消息目的地名称
    private Destination destination = null;  //在点对点（PTP）消息传递域中，目的地被成为队列（queue）
    private Connection connection = null;  //初始化 一个JMS客户端到JMS Provider的连接
    private Session session = null;  //初始化  一个发送消息的进程
    private MessageProducer producer = null;    //初始化 消息生产者 (它是由session 创建的)

    // 初始化
    private void initialize() throws JMSException, Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                user, password, url);
        connection = connectionFactory.createConnection();
        //false 参数表示 为非事务型消息，后面的参数表示消息的确认类型（见4.消息发出去后的确认模式）
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //PTP消息方式   目的地被成为队列（queue）
        destination = session.createQueue(subject);
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }


    // 发送消息
    public void produceMessage(String message) throws JMSException, Exception {
        initialize();
        TextMessage msg = session.createTextMessage(message);
        connection.start();
        System.out.println("Producer:->Sending message: " + message);
        producer.send(msg);
    }


    // 关闭连接

    public void close() throws JMSException {

        System.out.println("Producer:->Closing connection");

        if (producer != null)

            producer.close();

        if (session != null)

            session.close();

        if (connection != null)

            connection.close();

    }

}
