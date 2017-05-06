package love.celery.activemq.ptp.common;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午3:16
 */


public class Consumer {

  private String user = ActiveMQConnection.DEFAULT_USER;     //默认 用户
  private String password = ActiveMQConnection.DEFAULT_PASSWORD;    //默认 密码

  private String url = ActiveMQConnection.DEFAULT_BROKER_URL; //默认的是localhost:8080
  private String subject = "TOOL.DEFAULT"; //消息目的地名称
  private Destination destination = null;  //在点对点（PTP）消息传递域中，目的地被成为队列（queue）
  private Connection connection = null;  //初始化 一个JMS客户端到JMS Provider的连接
  private Session session = null;     //初始化  一个接受消息的进程
  private MessageConsumer consumer = null;   //初始化 消息消费者

  // 初始化
  private void initialize() throws JMSException, Exception {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
      user, password, url);
    connection = connectionFactory.createConnection();
    //false 参数表示 为非事务型消息，后面的参数表示消息的确认类型（见4.消息发出去后的确认模式）
    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    //PTP消息方式   目的地被成为队列（queue）
    destination = session.createQueue(subject);
    consumer = session.createConsumer(destination);
  }


  // 消费消息
  public void start() throws Exception {
    initialize();
    connection.start();
    System.out.println("Consumer:->Begin listening...");
    //接受消息方式2：消息的同步  主动接受消息 用recieve方式
    TextMessage textMessage = (TextMessage) consumer.receive();
    if (null != textMessage)
      System.out.println(textMessage.getText());
    else
      System.out.println("无");
  }

  // 关闭连接
  public void close() throws JMSException {
    System.out.println("Consumer:->Closing connection");
    if (consumer != null)
      consumer.close();
    if (session != null)
      session.close();
    if (connection != null)
      connection.close();
  }

  // 消息处理函数
  public void onMessage(Message message) {
    try {
      if (message instanceof TextMessage) {
        TextMessage txtMsg = (TextMessage) message;
        String msg = txtMsg.getText();
        System.out.println("Consumer:->Received: " + msg);
      } else {
        System.out.println("Consumer:->Received: " + message);
      }
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

}
