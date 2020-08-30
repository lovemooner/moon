package love.moon.j2se.thread.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author: lovemooner
 * Date: 2017/5/10 14:07
 */
public class ThreadPoolExecutorJDK100 {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutorJDK100 pool = new ThreadPoolExecutorJDK100();
        pool.testCachedThreadPool();
        pool.testFixedThreadPool();
//        pool.testScheduledThreadPool();
//        pool.testSingleThreadExecutor();
    }

    /**
     * 定长线程池
     *
     */
    public void testFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 1; i++) {
            fixedThreadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + ":Hi");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            });
        }
        System.out.println("end===================");
    }

    /**
     * 可缓存线程池
     *
     * @throws InterruptedException
     */
    public void testCachedThreadPool() throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ":Hi"));
        }
    }

    /**
     * 定时及周期性任务执行
     */
    public void testScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    public void testSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ": thread index-" + index);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }




}
