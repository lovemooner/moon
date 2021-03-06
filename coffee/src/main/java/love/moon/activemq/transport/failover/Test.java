package love.moon.activemq.transport.failover;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午4:16
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();
        producer.produceMessage("Hello, world!");
        Thread.sleep(500);
        consumer.start();
        producer.close();
        // 延时500毫秒之后停止接受消息
        Thread.sleep(500);
        consumer.close();

    }

}
