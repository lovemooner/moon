package love.moon.design.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Author: lovemooner
 * Date: 2017/5/8 13:57
 */
public class JdkDynamicProxy100 {


    public static void main(String[] args) {
        JdkDynamicProxy100 dynamicProxy100 = new JdkDynamicProxy100();
        dynamicProxy100.test();
    }


    public void test() {
        Subject subject = new RealSubject();
        //create proxy class
        Class<?> clazz = subject.getClass();
        ClassLoader loader = clazz.getClassLoader();
        Class<?>[] interfaces = clazz.getInterfaces();
        InvocationHandler handler = new Handler(subject);
        Subject proxy = (Subject) Proxy.newProxyInstance(loader, interfaces, handler);
        proxy.hello1("world");
    }


    class Handler implements InvocationHandler {

        private Object subject;

        public Handler(Object subject) {
            this.subject = subject;
        }

        /**
         * @param proxy  生成的代理对象
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

    class RealSubject implements Subject {

        @Override
        public void hello(String str) {
            System.out.println("hello: " + str);
        }

        @Override
        public void hello1(String str) {
            System.out.println("hello1");
        }
    }

    interface Subject {

        void hello(String str);
        void hello1(String str);
    }
}
