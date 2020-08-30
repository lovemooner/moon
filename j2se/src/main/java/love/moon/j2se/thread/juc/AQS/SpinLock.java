package love.moon.j2se.thread.juc.AQS;

/**
 * Author: lovemooner
 * Date: 2018/7/27 13:41
 */

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    //java中原子（CAS）操作
    AtomicReference<Thread> owner = new AtomicReference<Thread>();//持有自旋锁的线程对象
    private static int count;

    public void lock() {
        Thread cur = Thread.currentThread();
        while (!owner.compareAndSet(null, cur)) {
        }
    }

    public void unLock() {
        Thread cur = Thread.currentThread();
        owner.compareAndSet(cur, null);
    }


    public static void main(String[] args) throws InterruptedException {
        final SpinLock lock = new SpinLock();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    count++;
                    lock.unLock();
                }
            });
            t.start();
        }
        Thread.currentThread().sleep(1000);
        System.out.println(count);
    }


}



