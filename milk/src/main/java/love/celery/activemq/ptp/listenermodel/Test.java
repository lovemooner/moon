package love.celery.activemq.ptp.listenermodel;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午4:07
 */
public class Test {

    public static void main(String[] args) throws  Exception {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();
        //通过监听Listener 开始监听
        consumer.start();
        // 延时500毫秒之后发送消息
        Thread.sleep(500);
        producer.produceMessage("Hello, world!");
        producer.close();
        consumer.close();
    }

}
