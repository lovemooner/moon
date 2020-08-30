package love.moon.design.behavioral.observer.pubsub;

/**
 * @author dongnan
 * @date 2020/8/13 17:20
 */
public interface Publisher {
    /**
     * Publishes new message to PubSubService
     * @param message
     */
    void publish(Message message);
}
