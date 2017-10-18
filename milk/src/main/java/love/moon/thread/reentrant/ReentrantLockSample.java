package love.moon.thread.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/14 14:49
 */
public class ReentrantLockSample  {
   private ReentrantLock lock = new ReentrantLock();


    public void method1() {
        try {
            lock.lock();
            System.out.println("method1");
        } finally {
            lock.unlock();
        }
    }
  public void testLockInterruptibly() {
        try {
            lock.lockInterruptibly();
            System.out.println("method1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        try {
            lock.tryLock(1000l, TimeUnit.SECONDS);
            System.out.println("method1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method3()  {
        try {
            lock.tryLock();
            System.out.println("method1");
        } finally {
            lock.unlock();
        }
    }



    public static void main(String[] args) {
      new ReentrantLockSample().method1();
    }

}
