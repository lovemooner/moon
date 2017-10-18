package love.moon.thread.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 早期的CPU当中，是通过在总线上加LOCK#锁的形式来解决缓存不一致的问题。因为CPU和其他部件进行通信都是通过总线来进行的，
 * 如果对总线加LOCK#锁的话，也就是说阻塞了其他CPU对其他部件访问（如内存），从而使得只能有一个CPU能使用这个变量的内存。
 * 比如上面例子中 如果一个线程在执行 i = i +1，如果在执行这段代码的过程中，在总线上发出了LCOK#锁的信号，
 * 那么只有等待这段代码完全执行完毕之后，其他CPU才能从变量i所在的内存读取变量，然后进行相应的操作。这样就解决了缓存不一致的问题。
 * Author: lovemooner
 * Date: 2017/6/14 16:55
 */
public class ReentrantLockIncrease102 {
    private int inc = 0;
    private Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantLockIncrease102 test = new ReentrantLockIncrease102();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        test.increase();
                    }
                }

                ;
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
