package love.moon.thread.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/19 17:04
 */
public class ReentrantTest101 {
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
        new ReentrantTest101().method1();
    }
}
