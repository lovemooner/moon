package love.moon.j2se.thread.concurrent.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2018/5/14 14:49
 */
public class ReentrantLock100 {

    private ReentrantLock lock = new ReentrantLock();

    public void test1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get lock");
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " exit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void addPoolMonitor(){

    }


    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock100 demo = new ReentrantLock100();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.test1();

                }
            }, "T"+i).start();
        }
    }
}
