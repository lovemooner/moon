package love.moon.design.behavioral.observer.sub;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ndong on 2017/5/6.
 */
public class SubjectManagement {
    //一个记录 名字——主题类 的Map
    private Map<String, Producer> subjectList = new HashMap<String, Producer>();
    public void addSubject(String name, Producer subject) {
        subjectList.put(name, subject);
    }
    public void addSubject(Producer subject) {
        subjectList.put(subject.getClass().getName(), subject);
    }
    public Producer getSubject(String subjectName) {
        return subjectList.get(subjectName);
    }
    public void removeSubject(String name, Producer subject) {
    }
    public void removeSubject(Producer subject) {
    }
    //singleton
    private SubjectManagement() {}
    public static SubjectManagement getInstance() {
        return SubjectManagementInstance.instance;
    }
    private static class SubjectManagementInstance {
        static final SubjectManagement instance = new SubjectManagement();
    }
}
