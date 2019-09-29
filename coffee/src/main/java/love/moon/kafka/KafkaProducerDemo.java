package love.moon.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaProducerDemo {
    private final Producer<String, String> kafkaProducer;

    private KafkaProducerDemo() {
        kafkaProducer = createKafkaProducer();
    }

    private Producer<String, String> createKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConstants.BOOTSTRAP_SERVERS);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> kafkaProducer = new KafkaProducer<>(props);
        return kafkaProducer;
    }

    void produce() {
        for (int i = 0; i < 1; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String key = "key" + 1;
            String data = "hello kafka message:" + key;
            data="{\"devGbId\":\"861679030010861\",\"latitude\":31.295700440483515,\"longitude\":120.52045409201855,\"direction\":-1.0,\"altitude\":0.0,\"speed\":0.0,\"timestamp\":1567150360000,\"locFlag\":\"A\",\"csysType\":2,\"csysLon\":120.531339,\"csysLat\":31.299376,\"userCode\":\"042\",\"deviceType\":1,\"motionMode\":0,\"dynamicDevice\":1}";
            kafkaProducer.send(new ProducerRecord<>(KafkaConstants.TOPIC, key, data));
            System.out.println("producer:"+data);
        }
    }

    public static void main(String[] args) {
        new KafkaProducerDemo().produce();
    }
}
