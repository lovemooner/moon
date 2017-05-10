package love.moon.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: lovemooner
 * Date: 2017/5/8 13:51
 */
public class DynamicProxy implements InvocationHandler {

    private Object subject;

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    /**
     *
     * @param proxy 生成的代理对象
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("DynamicProxy->before");
        System.out.println("DynamicProxy-> invoke Method:" + method);
        method.invoke(subject, args);
        System.out.println("DynamicProxy->after");
        return null;
    }
}