package love.moon.thread.concurrent.AQS;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2018/7/27 15:39
 */
public class TestState {

    static Lock lock = new ReentrantLock();
    private static int count=0;

    public static void main(String[] args) throws InterruptedException {
        TestState t=new TestState();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        System.out.println("Thread:"+Thread.currentThread().getName());
                        count++;
                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(count);
    }
}
