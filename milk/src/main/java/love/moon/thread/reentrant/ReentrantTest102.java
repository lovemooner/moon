package love.moon.thread.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/14 14:49
 */
public class ReentrantTest102  {
    ReentrantLock lock = new ReentrantLock();


    public void method1() {
        try {
            lock.lock();
            method2();
            System.out.println("method1");
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        try {
            lock.lock();
            System.out.println("method2");
        } finally {
            lock.unlock();
        }
    }




    public static void main(String[] args) {
      new ReentrantTest102().method1();
    }

}
