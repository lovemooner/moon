package love.moon.thread.concurrent.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import love.moon.thread.concurrent.pool.monitor.ThreadPoolMonitor100;

import java.util.concurrent.*;

public class ThreadPoolExecutor101 {
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        new Thread(() -> ThreadPoolMonitor100.startMonitor(pool, 100L)).start();
        for (int i = 0; i < 10; i++) {
            pool.execute(new Task());
        }
    }


    static class Task implements Runnable {

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
