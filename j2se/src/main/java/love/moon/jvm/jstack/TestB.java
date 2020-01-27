package love.moon.jvm.jstack;

/**
 * Author: lovemooner
 * Date: 2018/5/31 16:52
 */
public class TestB {


    public synchronized void test()  {
        System.out.println("Thread="+Thread.currentThread().getName());
        try {
            Thread.sleep(1000000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("return");
    }


    public static void main(String[] args) throws InterruptedException {
        final TestB b=new TestB();
        new Thread(new Runnable() {
            public void run() {
                b.test();
            }
        },"t1").start();
        new Thread(new Runnable() {
            public void run() {
                b.test();
            }
        },"t2").start();

    }
}

