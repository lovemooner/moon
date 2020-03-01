package love.moon.j2se.thread.concurrent.ReentrantLock;

import love.moon.j2se.thread.concurrent.pool.monitor.ThreadPoolMonitor100;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/9/14 17:14
 */
public class ThreadPoolMonitor101 {
    private ExecutorService executor = Executors.newCachedThreadPool();


    public ThreadPoolMonitor101() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ThreadPoolMonitor100.startMonitor(executor,1000l);
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (((ThreadPoolExecutor) executor).getCompletedTaskCount() == 10) break;
                }
            }
        }).start();
    }


    public void test() throws InterruptedException {
        final Active active = new Active();
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {   //复用执行第一个任务的线程
                public void run() {
//                    active.synFun();
                    active.testReentrantLock();

                }
            });
        }

    }


    public static void main(String[] args) throws InterruptedException {
        ThreadPoolMonitor101 monitor = new ThreadPoolMonitor101();
        monitor.test();

    }

    class Active {

        private Lock lock = new ReentrantLock();

        public synchronized void synFun() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        public void testReentrantLock() {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }


    }

}
