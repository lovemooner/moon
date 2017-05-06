package love.moon.design.observer.sub;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by ndong on 2017/5/6.
 */
public class ObserverTest {
    private static Producer publisher;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        publisher = new MyPublisher();
        //添加一个名为Linus的作家
        SubjectManagement.getInstance().addSubject("Linus",publisher);
    }
    @Test
    public void test() {
        //定义几个读者
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        consumer1.setSubject(publisher);
        consumer2.setSubject(publisher);
        consumer1.subscribe("Linus");
        consumer2.subscribe("Linus");
        publisher.publish("I have a new Changed");
    }
}
