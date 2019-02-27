package love.moon.kafka.transaction;

import kafka.common.KafkaException;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;

public class ProducerTransactionalExample {

    public final static String TOPIC = "KAFKA_TRANSACTION1";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("client.id", "ProducerTransactionalExample");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("transactional.id", "test-transactional");
        props.put("acks", "all");
        KafkaProducer producer = new KafkaProducer(props);
        producer.initTransactions();
        try {
            String msg = "matt test";
            producer.beginTransaction();
            producer.send(new ProducerRecord(TOPIC, "0", msg.toString()));
            producer.send(new ProducerRecord(TOPIC, "1", msg.toString()));
            producer.send(new ProducerRecord(TOPIC, "2", msg.toString()));
            producer.commitTransaction();
        } catch (ProducerFencedException e1) {
            e1.printStackTrace();
            producer.close();
        } catch (KafkaException e2) {
            e2.printStackTrace();
            producer.abortTransaction();
        }
        producer.close();
    }
}
