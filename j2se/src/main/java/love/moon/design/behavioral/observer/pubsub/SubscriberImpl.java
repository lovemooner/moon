package love.moon.design.behavioral.observer.pubsub;

/**
 * @author dongnan
 * @date 2020/8/13 17:24
 */
public class SubscriberImpl extends Subscriber {

    PubSubService pubSubService=PubSubService.getInstance();

    @Override
    public void subscribe(String topic) {
        pubSubService.addSubscriber(topic, this);
    }

    @Override
    public void unSubscribe(String topic) {
        pubSubService.removeSubscriber(topic, this);
    }

    @Override
    public void pull(String topic) {
        pubSubService.getMessagesForSubscriberOfTopic(topic, this);

    }
}
