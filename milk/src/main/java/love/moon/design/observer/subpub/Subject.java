package love.moon.design.observer.subpub;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by ndong on 2017/5/6.
 */
public class Subject {
    private Map<String,Publisher> publisherList = new HashMap<String, Publisher>();

    public void addPublisher(String name, Publisher publisher) {
        publisherList.put(name, publisher);
    }

    public void addPublisher(Publisher publisher) {
        publisherList.put(publisher.getClass().getName(), publisher);
    }

    public Publisher getSubject(String publisherName) {
        return publisherList.get(publisherName);
    }

    public void removeSubject(String name, love.moon.design.observer.Publisher subject) {
    }

    public void removeSubject(love.moon.design.observer.Publisher subject) {
    }

    //singleton
    private Subject() {
    }

    public static Subject getInstance() {
        return SubjectInstance.instance;
    }

    private static class SubjectInstance {
        static final Subject instance = new Subject();
    }

}
