package love.moon.j2se.thread.juc.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import love.moon.j2se.thread.juc.pool.monitor.ThreadPoolMonitor100;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lovemooner
 */
@Slf4j
public class ThreadPoolExecutor100 {
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(3, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        new Thread(() -> ThreadPoolMonitor100.startMonitor(pool, 100L)).start();
        for (int i = 0; i < 2; i++) {
            pool.execute(new Task());
        }
    }


    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (Task.class) {
                try {
//                Thread.sleep(100000l);
                    Thread.currentThread().wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(Thread.currentThread().getName() + " end");
        }

    }




}
