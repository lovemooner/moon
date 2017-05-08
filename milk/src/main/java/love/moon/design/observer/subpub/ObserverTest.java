package love.moon.design.observer.subpub;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by ndong on 2017/5/6.
 */
public class ObserverTest {
    private static MySubject writer;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        writer = new MySubject();
        //添加一个名为Linus的作家
        SubjectManagement.getInstance().addSubject("Linus",writer);
    }
    @Test
    public void test() {
        //定义几个读者
        MyObserver reader1 = new MyObserver();
        MyObserver reader2 = new MyObserver();
        reader1.subscribe("Linus");
        reader2.subscribe("Linus");
        writer.publish("publish a new Message");
    }
}
