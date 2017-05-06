package love.moon.design.observer;

/**
 * Created by ndong on 2017/5/6.
 */
public class MyObserver implements Observer {
    private Publisher publisher;
    // get the notify message from Concentrate Subject
    @Override
    public void update() {
        String message = publisher.getMessage();
        System.out.println("From publisher " + publisher.getClass().getName()
                + " message: " + message);
    }
    @Override
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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
