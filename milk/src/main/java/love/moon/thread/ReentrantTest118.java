package love.moon.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/14 14:49
 */
public class ReentrantTest118 implements Runnable {
    ReentrantLock lock = new ReentrantLock();


    public void get() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getId());
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getId());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        get();
    }


    public static void main(String[] args) {
        ReentrantTest118 ss = new ReentrantTest118();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }

}
