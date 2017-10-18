package love.moon.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/10/18 14:05
 */
public class Synchronized100 {

    public synchronized void test() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    System.out.println("running");
                }
            }
        });
        t.start();
        t.interrupt();
        System.out.println("end========");
    }

    public void test1() {
        final ReentrantLock lock = new ReentrantLock();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    Thread.sleep(3000l);
                    System.out.println("wakeup");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        t.start();
        t.interrupt();
        System.out.println("end");
    }

    public void test2() {
        final ReentrantLock lock = new ReentrantLock();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    Thread.sleep(3000l);
                    System.out.println("wakeup");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        t.start();
        t.interrupt();
        System.out.println("end");
    }


    public static void main(String[] args) {
        Synchronized100 demo = new Synchronized100();
        demo.test();
    }

}
