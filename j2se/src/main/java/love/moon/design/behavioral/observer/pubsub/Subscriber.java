package love.moon.design.behavioral.observer.pubsub;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dongnan
 * @date 2020/8/13 17:22
 */
public abstract class Subscriber {
    /**
     * store all messages received by the subscriber
     */
    private List<Message> subscriberMessages = new ArrayList<>();

    public List<Message> getSubscriberMessages() {
        return subscriberMessages;
    }
    public void setSubscriberMessages(List<Message> subscriberMessages) {
        this.subscriberMessages = subscriberMessages;
    }

    /**
     * Add subscriber with PubSubService for a topic
     * @param topic
     */
    public abstract void subscribe(String topic);

    /**
     * Unsubscribe subscriber with PubSubService for a topic
     * @param topic
     */
    public abstract void unSubscribe(String topic);

    /**
     * Request specifically for messages related to topic from PubSubService
     * @param topic
     */
    public abstract void pull(String topic);

    /**
     * Broadcast new messages added in queue to All subscribers of the topic. messagesQueue will be empty after broadcasting
     */
    public void printMessages(){
        for(Message message : subscriberMessages){
            System.out.println("Message Topic -> "+ message.getTopic() + " : " + message.getPayload());
        }
    }
}
