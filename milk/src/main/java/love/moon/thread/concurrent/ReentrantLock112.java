package love.moon.thread.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/14 15:52
 */
public class ReentrantLock112 {
    private Lock lock = new ReentrantLock();

    public void testLockInterruptibly() {
        try {
            lock.lockInterruptibly();
            while (true) {
//                System.out.println(Thread.currentThread().getName() + ":==");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


    public static void main(String[] args) throws Exception {
        final ReentrantLock112 ss = new ReentrantLock112();
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

    }
}
