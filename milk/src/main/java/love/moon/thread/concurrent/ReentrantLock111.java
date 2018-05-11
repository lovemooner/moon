package love.moon.thread.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/14 15:29
 */
public class ReentrantLock111 {

    private Lock lock = new ReentrantLock();

    public void testLockInterruptibly() throws Exception {
        lock.lock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    while (true) System.out.println("==lockInterruptibly==");

                } catch (InterruptedException e) {
                    System.out.println("exception: " + Thread.currentThread().getName() + " interrupted.");
                }
            }
        }, "thread -1");
        t1.start();
        Thread.sleep(5000);
//        t1.interrupt();
//        Thread.sleep(10000);
    }

    public static void main(String[] args) throws Exception {
        ReentrantLock111 ss = new ReentrantLock111();
        ss.testLockInterruptibly();
    }
}
