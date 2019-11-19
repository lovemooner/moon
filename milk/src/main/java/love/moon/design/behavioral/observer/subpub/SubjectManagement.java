package love.moon.design.behavioral.observer.subpub;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ndong on 2017/5/6.
 */
public class SubjectManagement {
    //一个记录 名字——主题类 的Map
    private Map<String, Subject> subjectList = new HashMap<String, Subject>();
    public void addSubject(String name, Subject subject) {
        subjectList.put(name, subject);
    }
    public void addSubject(Subject subject) {
        subjectList.put(subject.getClass().getName(), subject);
    }
    public Subject getSubject(String subjectName) {
        return subjectList.get(subjectName);
    }
    public void removeSubject(String name, Subject subject) {
    }
    public void removeSubject(Subject subject) {
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
