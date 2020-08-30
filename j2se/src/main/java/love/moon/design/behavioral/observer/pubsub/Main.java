package love.moon.design.behavioral.observer.pubsub;

/**
 * @author dongnan
 * @date 2020/8/13 17:26
 */
public class Main {

    public static void main(String[] args) {
        Publisher javaPublisher = new PublisherImpl();
        Publisher pythonPublisher = new PublisherImpl();
        Subscriber javaSubscriber = new SubscriberImpl();
        Subscriber pythonSubscriber = new SubscriberImpl();
        Subscriber allLanguagesSubscriber = new SubscriberImpl();

        //subscribe topic
        javaSubscriber.subscribe("Java");
        pythonSubscriber.subscribe("Python");
        allLanguagesSubscriber.subscribe("Java");
        allLanguagesSubscriber.subscribe("Python");

        //Publish Messages. message-->Queue
        Message javaMsg1 = new Message("Java", "Core Java Concepts");
        Message pythonMsg1 = new Message("Python", "Easy and Powerful programming language");
        javaPublisher.publish(javaMsg1);
        pythonPublisher.publish(pythonMsg1);
        //Broadcast message.Queue-->subscriber
        PubSubService.getInstance().broadcast();
        javaSubscriber.printMessages();
        pythonSubscriber.printMessages();
        allLanguagesSubscriber.printMessages();

        Message javaMsg2 = new Message("Java", "JSP and Servlets");
        javaPublisher.publish(javaMsg2);
        javaSubscriber.pull("Java");
        javaSubscriber.printMessages();
    }
}
