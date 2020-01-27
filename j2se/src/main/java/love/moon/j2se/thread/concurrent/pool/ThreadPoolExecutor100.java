package love.moon.j2se.thread.concurrent.pool;

import love.moon.j2se.thread.concurrent.pool.monitor.ThreadPoolMonitor100;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * Author: lovemooner
 * Date: 2018/8/17 17:10
 */
public class ThreadPoolExecutor100 {

    public static void main(String[] args) {
        ThreadPoolExecutor100 executor100 = new ThreadPoolExecutor100();
        executor100.mockFixPool();
    }


    /**
     * FixPool
     * 1）queueSize=Integer.MAX_VALUE？
     *    当超出BQueue的size，一样会创建大量线程，所以应设大点。
     * 2）	keepAliveTime为什么为0？
     * keepAliveTime针对worker，超过的进入blockQueue，设置没意义。
     */
    public void mockFixPool() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 40, 3,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        new Thread(() -> ThreadPoolMonitor100.startMonitor(pool,100L)).start();
        for (int i = 1; i <5; i++) {
            pool.submit(new Task());
        }
    }

    public void testShutDown() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 40, 3,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));

        new Thread(() -> ThreadPoolMonitor100.startMonitor(pool,100L)).start();

        for (int i = 1; i <5; i++) {
                pool.submit(new Task());
        }
        pool.shutdownNow();
    }


    class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }

    }
}