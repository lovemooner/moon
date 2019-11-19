package love.moon.design.behavioral.observer.subpub;

/**
 * Created by ndong on 2017/5/6.
 */
public class MyObserver implements Observer {
    @Override
    public void update(MySubject subject, Object arg) {
        String message = subject.getMessage();
        System.out.println("Observer: " + subject.getClass().getName()
                + " message: " + message);
    }

    // subcirbe some Subject
    public void subscribe(String subjectName) {
        SubjectManagement.getInstance().getSubject(subjectName).register(this);
    }

    // cancel subcribe
    public void cancelSubcribe(String subjectName) {
        SubjectManagement.getInstance().getSubject(subjectName).remove(this);
    }
}
