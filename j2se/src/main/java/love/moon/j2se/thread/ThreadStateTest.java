package love.moon.j2se.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongnan
 * @date 2020/5/2 1:56
 */
public class ThreadStateTest {

    public static void main(String[] args) throws InterruptedException {
        TestLock testLock = new TestLock();
        Thread thread1 = new Thread(() -> testLock.myTestLock(), "thread1");
        Thread thread2 = new Thread(() -> testLock.myTestLock(), "thread2");

        thread1.start();
        Thread.sleep(1000);

        thread2.start();
        Thread.sleep(1000);

        System.out.println("****" + (thread2.getState()));
        Thread.sleep(20000);
    }

    static class TestLock {
        private final Lock lock = new ReentrantLock();

        public void myTestLock() {
            lock.lock();
            try {
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName()+" testLock status");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        }
    }

}

