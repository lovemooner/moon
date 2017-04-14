package love.celery.activemq.transport.failover;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午4:28
 */

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
public  class DemoListener implements MessageListener {
  private static final Logger logger = LoggerFactory.getLogger(DemoListener.class);

    private String user = ActiveMQConnection.DEFAULT_USER;     //默认 用户
  private String password = ActiveMQConnection.DEFAULT_PASSWORD;    //默认 密码

  private String url = ActiveMQConnection.DEFAULT_BROKER_URL; //默认的是localhost:8080

  private String producerType;

  public DemoListener(String producerType) {
    this.producerType = producerType;
  }

     /**
   * 消息处理函数
   *
   * @param message
   */
  public void onMessage(Message message) {
    //do 回调
  }

  public void start() throws Exception {
    logger.info("startup listening...,producerType:{}", producerType);

    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
      user, password, url);
    Connection connection = connectionFactory.createConnection();
    ((ActiveMQConnection) connection).addTransportListener(new ClientTransportListener());
    //false 参数表示 为非事务型消息，后面的参数表示消息的确认类型（见4.消息发出去后的确认模式）
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    String subject = "Test";
    MessageConsumer consumer = null;
    if ("QUEUE".equals(producerType)) {
      Destination destination = session.createQueue(subject);
      consumer = session.createConsumer(destination);
    } else {
      Topic topic = session.createTopic(subject);
      consumer = session.createConsumer(topic);
    }
    consumer.setMessageListener(this);
    connection.start();
    logger.info("startup success");
  }

}
