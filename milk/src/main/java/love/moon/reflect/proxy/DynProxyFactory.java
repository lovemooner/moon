package love.moon.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Author: lovemooner
 * Date: 2017/5/8 13:52
 */
public class DynProxyFactory {

    public static Subject getInstance(){
        Subject subject = new RealSubject();
        InvocationHandler handler = new DynamicProxy(subject);
        Subject proxy = null;
        proxy = (Subject) Proxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                handler);
        return proxy;
    }
}