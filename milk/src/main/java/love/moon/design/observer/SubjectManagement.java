package love.moon.design.observer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ndong on 2017/5/6.
 */
public class SubjectManagement {
    //一个记录 名字——主题类 的Map
    private Map<String, Publisher> publisherList = new HashMap<String, Publisher>();

    public void addPublisher(String name, Publisher subject) {
        publisherList.put(name, subject);
    }

    public void addPublisher(Publisher publisher) {
        publisherList.put(publisher.getClass().getName(), publisher);
    }

    public Publisher getSubject(String publisherName) {
        return publisherList.get(publisherName);
    }

    public void removeSubject(String name, Publisher subject) {
    }

    public void removeSubject(Publisher subject) {
    }

    //singleton
    private SubjectManagement() {
    }

    public static SubjectManagement getInstance() {
        return SubjectManagementInstance.instance;
    }

    private static class SubjectManagementInstance {
        static final SubjectManagement instance = new SubjectManagement();
    }
}
