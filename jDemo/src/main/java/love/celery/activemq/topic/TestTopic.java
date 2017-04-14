package love.celery.activemq.topic;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午3:56
 */
public class TestTopic {

    public static void main(String[] args) throws Exception {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();
        consumer.consumeMessage();
        producer.produceMessage("Hello, topic!");
        producer.close();
        consumer.close();

    }

}
