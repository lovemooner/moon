package love.moon.activemq.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * Created by lovemooner on 2017/5/6.
 */
public class MyBrokerService {

    public static void main(String[] args) throws Exception {
        BrokerService broker =new BrokerService();
        broker.setBrokerName("testName");//如果启动多个Broker时，必须为Broker设置一个名称
        broker.addConnector("tcp://localhost:61616");
        broker.start();
    }
}
