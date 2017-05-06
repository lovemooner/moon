package love.moon.design.observer.subpub;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by ndong on 2017/5/6.
 */
public class Main {
    private static Publisher publisher;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        publisher = new Publisher();
        //添加一个名为Linus的作家
        Subject.getInstance().addPublisher("Linus",publisher);
    }

    @Test
    public void test() {

        //定义几个读者
        Subscriber reader1 = new Subscriber();
        Subscriber reader2 = new Subscriber();
        Subscriber reader3 = new Subscriber();
        reader1.subscribe("Linus");
        reader2.subscribe("Linus");
        reader3.subscribe("Linus");
        publisher.publish(1);
    }
}
