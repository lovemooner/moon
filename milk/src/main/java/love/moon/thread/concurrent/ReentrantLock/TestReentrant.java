package love.moon.thread.concurrent.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试ReentrantLock的可重入性，也叫做递归锁
 * Author: lovemooner
 * Date: 2017/9/19 17:04
 */
public class TestReentrant {
    ReentrantLock lock = new ReentrantLock();


    private void method1() {
        try {
            lock.lock();
            System.out.println("method1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    private void method2() {
        try {
            lock.lock();
            System.out.println("method2");
        } finally {
            lock.unlock();
        }
    }

    public void method3() {
        synchronized (TestReentrant.class) {
            System.out.println("method3");
            method4();
        }
    }

    public void method4() {
        synchronized (TestReentrant.class) {
            System.out.println("method4");
        }
    }


    public static void main(String[] args) {
        new TestReentrant().method1();
        new TestReentrant().method3();

    }
}
