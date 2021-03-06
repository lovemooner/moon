package love.moon.j2se.thread.native1.ThreadLocal;

/**
 * Author: lovemooner
 * Date: 2018/5/29 16:41
 */
public class ThreadLocal100 {
    private ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public void set() {
        stringLocal.set(Thread.currentThread().getName());
    }


    public String get() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocal100 test = new ThreadLocal100();
        test.set();
        System.out.println(test.get());


        Thread thread1 = new Thread() {
            public void run() {
                test.set();
                System.out.println(test.get());
            }

            ;
        };
        thread1.start();
        thread1.join();

        System.out.println(test.get());
    }
}
