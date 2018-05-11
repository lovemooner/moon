package love.moon.thread.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/10/18 14:44
 */
public class ReentrantLock999 {

    private Lock lock = new ReentrantLock();

    public void testLock() {
        lock.lock();
        try {
            while (true){
                System.out.println("currentThread:"+Thread.currentThread().getName());
            }

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock999 test = new ReentrantLock999();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.testLock();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.testLock();
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000l);
        System.out.println("t1 isAlive:"+t1.getState()+",t2 isAlive:"+t2.getState());
        t2.interrupt();

        System.out.println("end===");
    }
}
