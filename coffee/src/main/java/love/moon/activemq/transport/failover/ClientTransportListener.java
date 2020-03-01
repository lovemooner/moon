package love.moon.activemq.transport.failover;

/**
 *
 * activeMQ可以建立长连接，网络状态的不稳定，可能随时中断连接。
 * Failover Transport是一种重新连接的机制，它工作于其它transport的上层，用于建立可靠的传输。它的配置语法允许制定任意多个复合的URI。
 *Failover transport会自动选择其中的一个URI来尝试建立连接。如果没有成功，那么会选择一个其它的URI来建立一个新的连接。例如：
 *failover:(tcp://primary:61616,tcp://secondary:61616)?randomize=false
 *
 *
* User: lovemooner
* Date: 17-3-27
* Time: 下午4:11
*/

import org.apache.activemq.transport.TransportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ClientTransportListener implements TransportListener {

  protected final Logger logger = LoggerFactory.getLogger(ClientTransportListener.class);

  /**
   * 对消息传输命令进行监控
   */
  public void onCommand(Object o) {
    logger.debug("onCommand检测到服务端命令:{}", o);
  }

  /**
   * 与服务器连接发生错误
   *
   * @param error
   */
  public void onException(IOException error) {
    logger.error("onException,与服务器连接发生错误......");
  }

  /**
   * 消息服务器连接发生中断
   */
  public void transportInterupted() {
    logger.error("transportInterupted,与服务器连接发生中断......");
    IConnector connector = new Connector();
      try {
          connector.reConnect();
      } catch (Exception e) {
          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
  }

  /**
   * 恢复与服务器的连接
   */
  public void transportResumed() {
    logger.info("transportResumed,恢复与服务器连接....");
  }

}
