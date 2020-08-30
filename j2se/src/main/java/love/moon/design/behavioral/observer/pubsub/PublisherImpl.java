package love.moon.design.behavioral.observer.pubsub;

/**
 * @author dongnan
 * @date 2020/8/13 17:21
 */
public class PublisherImpl implements Publisher {

    PubSubService pubSubService = PubSubService.getInstance();

    @Override
    public void publish(Message message ) {
        pubSubService.addMessageToQueue(message);
    }
}
