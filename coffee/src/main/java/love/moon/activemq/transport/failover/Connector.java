package love.moon.activemq.transport.failover;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午4:25
 */
public class Connector implements IConnector {

  /**
   * @throws InterruptedException
   */
  public void reConnect() throws Exception {
      DemoListener listener = new DemoListener("QUEUE");
      listener.start();
  }


}