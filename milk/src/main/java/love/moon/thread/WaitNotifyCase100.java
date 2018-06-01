package love.moon.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/19 15:50
 */
public class WaitNotifyCase100 {
    public static void main(String[] args) {
         final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A Running...");
                synchronized (lock) {
                    try {
                        System.out.println("A get lock");
                        TimeUnit.SECONDS.sleep(3);
                        lock.wait();
                        System.out.println("A wait end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B Running...");
                synchronized (lock) {
                    System.out.println("B get lock");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("B do notify method");
                    lock.notify();
                    try {
                        Thread.sleep(10000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("B return");
                }

            }
        }).start();
    }
}
