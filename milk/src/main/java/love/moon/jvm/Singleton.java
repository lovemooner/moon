package love.moon.jvm;

/**
 * Author: lovemooner
 * Date: 2018/1/9 17:50
 */
public class Singleton {
    private volatile static Singleton uniqueInstance;

    private Singleton() {
        try {
            System.out.println("Singleton()->" + Thread.currentThread().getName());
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            System.out.println("uniqueInstance is null->" + Thread.currentThread().getName());
            synchronized (Singleton.class) {
                System.out.println("synchronized block->" + Thread.currentThread().getName());
                if (uniqueInstance == null) {
                    System.out.println("new instance->" + Thread.currentThread().getName());
                    uniqueInstance = new Singleton();
                    // A线程被指令重排了，刚好先赋值了；但还没执行完构造函数。
                }
            }
        }
        return uniqueInstance;// 后面B线程执行时将引发：对象尚未初始化错误。
    }

    public void test() {
//        System.out.println("Hello!");
    }


}
