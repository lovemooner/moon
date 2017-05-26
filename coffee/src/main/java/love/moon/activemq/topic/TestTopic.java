package love.moon.activemq.topic;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午3:56
 */
public class TestTopic {

    public static void main(String[] args) throws Exception {
        Consumer consumer1 = new Consumer("consumer1");
        Consumer consumer2 = new Consumer("consumer2");
        Producer producer = new Producer();
        consumer1.consumeMessage();
        consumer2.consumeMessage();
        producer.produceMessage("Hello, topic!");
        producer.close();
        consumer1.close();
        consumer2.close();

    }

}
