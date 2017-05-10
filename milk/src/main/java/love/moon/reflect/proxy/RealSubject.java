package love.moon.reflect.proxy;

/**
 * Author: lovemooner
 * Date: 2017/5/8 13:55
 */
public class RealSubject implements Subject {


    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}