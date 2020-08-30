package love.moon.j2se.thread.juc.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/19 17:03
 */
public class ReentrantLockSample {
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 最简单的demo
     */
    public void test1() {
        try {
            lock.lock();
            System.out.println("method1");
        } finally {
            lock.unlock();
        }
    }



    public static void main(String[] args) {
        new ReentrantLockSample().test1();
    }
}