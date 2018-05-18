package love.moon.thread.concurrent.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2018/5/14 14:49
 */
public class TestLock {

    private ReentrantLock lock = new ReentrantLock();

    public void test1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000L);
            System.out.println(Thread.currentThread().getName() + " ready to exit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final TestLock demo = new TestLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.test1();

            }
        }, "T1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.test1();

            }
        }, "T2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.test1();

            }
        }, "T3").start();
    }
}
