package love.moon.thread;

/**
 * Author: lovemooner
 * Date: 2018/5/10 11:25
 */
public class DeadlockSample {

    //必须有两个可以被加锁的对象才能产生死锁，只有一个不会产生死锁问题
    private final Object obj1 = new Object();
    private final Object obj2 = new Object();

    public static void main(String[] args) {
        DeadlockSample test = new DeadlockSample();
        test.testDeadlock();
    }

    private void testDeadlock() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                calLock_Obj1_First();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                calLock_Obj2_First();
            }
        });
        t1.start();
        t2.start();
    }

    //先synchronized  obj1，再synchronized  obj2
    private void calLock_Obj1_First() {
        synchronized (obj1) {
            sleep();
            System.out.println(Thread.currentThread().getName() + ":try get lock obj2");
            synchronized (obj2) {
                sleep();
                System.out.println(Thread.currentThread().getName() + ":done");
            }
        }
    }

    //先synchronized  obj2，再synchronized  obj1
    private void calLock_Obj2_First() {
        synchronized (obj2) {
            sleep();
            System.out.println(Thread.currentThread().getName() + ":try get lock obj1");
            synchronized (obj1) {
                sleep();
                System.out.println(Thread.currentThread().getName() + ":done");
            }
        }
    }

    /**
     * 为了便于让两个线程分别锁住其中一个对象，
     * 一个线程锁住obj1，然后一直等待obj2，
     * 另一个线程锁住obj2，然后一直等待obj1，
     * 然后就是一直等待，死锁产生
     */
    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
