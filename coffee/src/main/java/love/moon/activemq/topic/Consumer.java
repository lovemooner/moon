package love.moon.activemq.topic;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午3:48
 */
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Consumer implements MessageListener {

  private String name;

  private String user = ActiveMQConnection.DEFAULT_USER;     //默认 用户
  private String password = ActiveMQConnection.DEFAULT_PASSWORD;    //默认 密码

  private String url = ActiveMQConnection.DEFAULT_BROKER_URL; //默认的是localhost:8080
  private String subject = "DEMO.TOPIC"; //消息目的地名称
  private Topic topic = null;//在发布/订阅（PUB/SUB）消息传递域中，目的地被成为主题（topic）。
  private Connection connection = null;  //初始化 一个JMS客户端到JMS Provider的连接
  private Session session = null;     //初始化  一个接受消息的进程
  private MessageConsumer consumer = null;   //初始化 消息消费者


  public Consumer(String name){
    this.name=name;
  }

  // 初始化

  private void initialize() throws JMSException, Exception {
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
      user, password, url);
    connection = connectionFactory.createConnection();
    //false 参数表示 为非事务型消息，后面的参数表示消息的确认类型（见4.消息发出去后的确认模式）
    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    topic = session.createTopic(subject);
    consumer = session.createConsumer(topic);
  }

  // 消费消息
  public void consumeMessage() throws JMSException, Exception {
    initialize();
    connection.start();
    System.out.println("Consumer:->Begin listening...");
    consumer.setMessageListener(this);
  }


  // 关闭连接

  public void close() throws JMSException {
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
        System.out.println("Consumer "+name+":->Received: " + msg);
      } else {
        System.out.println("Consumer "+name+":->Received: " + message);
      }
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

}