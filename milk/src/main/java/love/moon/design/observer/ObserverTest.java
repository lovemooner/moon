package love.moon.design.observer;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by ndong on 2017/5/6.
 */
public class ObserverTest {
    private static MyPublisher publisher;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        publisher = new MyPublisher();
        //添加一个名为Linus的作家
        SubjectManagement.getInstance().addPublisher("Linus",publisher);
    }
    @Test
    public void test() {
        //定义几个读者
        MyObserver reader1 = new MyObserver();
        MyObserver reader2 = new MyObserver();
        MyObserver reader3 = new MyObserver();
        reader1.setPublisher(publisher);
        reader2.setPublisher(publisher);
        reader3.setPublisher(publisher);
        reader1.subscribe("Linus");
        reader2.subscribe("Linus");
        reader3.subscribe("Linus");
        publisher.makeChanged("I have a new Changed");
        reader1.update();
    }
}
