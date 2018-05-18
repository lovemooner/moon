package love.moon.thread.concurrent.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/14 15:52
 */
public class TestLockInterruptibly {
    private Lock lock = new ReentrantLock();

    public void testLockInterruptibly() {
        try {
            lock.lockInterruptibly();
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":==");
                Thread.sleep(500L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


    public static void main(String[] args) throws Exception {
        final TestLockInterruptibly ss = new TestLockInterruptibly();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                ss.testLockInterruptibly();
            }
        });
        t1.start();
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                ss.testLockInterruptibly();
            }
        });
        t2.start();
        t1.interrupt();
        System.out.println("end");
        t2.interrupt();
    }
}
