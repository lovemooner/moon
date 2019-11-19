package love.moon.design.behavioral.observer.sub;

/**
 * Created by ndong on 2017/5/6.
 */
public class Consumer implements MessageListener {
    private Producer subject;
    // get the notify message from Concentrate Subject
    @Override
    public void update() {
        String message = subject.getMessage();
        System.out.println("Consumer->received: " + message);
    }
    @Override
    public void setSubject(Producer subject) {
        this.subject = subject;
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
