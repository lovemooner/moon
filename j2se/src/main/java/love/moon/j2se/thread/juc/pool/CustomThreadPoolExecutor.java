package love.moon.j2se.thread.juc.pool;

import love.moon.j2se.thread.juc.pool.monitor.ThreadPoolMonitor100;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * Author: lovemooner
 * Date: 2018/8/17 17:10
 */
public class CustomThreadPoolExecutor {

    public static void main(String[] args) {
        CustomThreadPoolExecutor executor100 = new CustomThreadPoolExecutor();
        executor100.mockFixPool();
    }


    public void mockFixPool() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        new Thread(() -> ThreadPoolMonitor100.startMonitor(pool,100L)).start();
        for (int i = 0; i <50; i++) {
            pool.submit(new MyTask());
        }
    }

    public void testShutDown() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 40, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));

        new Thread(() -> ThreadPoolMonitor100.startMonitor(pool,100L)).start();

        for (int i = 1; i <5; i++) {
                pool.submit(new MyTask());
        }
        pool.shutdownNow();
    }


    class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }

    }
}