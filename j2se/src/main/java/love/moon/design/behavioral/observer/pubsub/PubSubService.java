package love.moon.design.behavioral.observer.pubsub;

import java.util.*;

/**
 * @author dongnan
 * @date 2020/8/13 17:22
 */

public class PubSubService {
    /**
     * Keeps set of subscriber topic wise, using set to prevent duplicates
     */
    Map<String, Set<Subscriber>> subscribersTopicMap = new HashMap<>();

    Queue<Message> messagesQueue = new LinkedList<>();

    private static PubSubService instance;

    public static synchronized PubSubService getInstance() {
        if (instance == null) {
            instance = new PubSubService();
        }
        return instance;
    }

    public void addMessageToQueue(Message message) {
        messagesQueue.add(message);
    }

    /**
     * Add a new Subscriber for a topic
     *
     * @param topic
     * @param subscriber
     */
    public void addSubscriber(String topic, Subscriber subscriber) {

        if (subscribersTopicMap.containsKey(topic)) {
            Set<Subscriber> subscribers = subscribersTopicMap.get(topic);
            subscribers.add(subscriber);
            subscribersTopicMap.put(topic, subscribers);
        } else {
            Set<Subscriber> subscribers = new HashSet<>();
            subscribers.add(subscriber);
            subscribersTopicMap.put(topic, subscribers);
        }
    }

    //Remove an existing subscriber for a topic
    public void removeSubscriber(String topic, Subscriber subscriber) {

        if (subscribersTopicMap.containsKey(topic)) {
            Set<Subscriber> subscribers = subscribersTopicMap.get(topic);
            subscribers.remove(subscriber);
            subscribersTopicMap.put(topic, subscribers);
        }
    }

    /**
     * Broadcast new messages added in queue to All subscribers of the topic.
     * messagesQueue will be empty after broadcasting
     */
    public void broadcast() {
        if (messagesQueue.isEmpty()) {
            System.out.println("No messages from publishers to display");
        } else {
            while (!messagesQueue.isEmpty()) {
                Message message = messagesQueue.remove();
                String topic = message.getTopic();

                Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);

                for (Subscriber subscriber : subscribersOfTopic) {
                    //add broadcasted message to subscribers message queue
                    List<Message> subscriberMessages = subscriber.getSubscriberMessages();
                    subscriberMessages.add(message);
                    subscriber.setSubscriberMessages(subscriberMessages);
                }
            }
        }
    }

    /**
     * Sends messages about a topic for subscriber at any point
     * @param topic
     * @param subscriber
     */
    public void getMessagesForSubscriberOfTopic(String topic, Subscriber subscriber) {
        if (messagesQueue.isEmpty()) {
            System.out.println("No messages from publishers to display");
        } else {
            while (!messagesQueue.isEmpty()) {
                Message message = messagesQueue.remove();

                if (message.getTopic().equalsIgnoreCase(topic)) {

                    Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);

                    for (Subscriber _subscriber : subscribersOfTopic) {
                        if (_subscriber.equals(subscriber)) {
                            //add broadcasted message to subscriber message queue
                            List<Message> subscriberMessages = subscriber.getSubscriberMessages();
                            subscriberMessages.add(message);
                            subscriber.setSubscriberMessages(subscriberMessages);
                        }
                    }
                }
            }
        }
    }

}