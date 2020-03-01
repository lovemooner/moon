package love.moon.j2se.thread.concurrent.pool.monitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author: lovemooner
 * Date: 2017/9/14 16:29
 */
public class ThreadPoolMonitor102 {

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public ThreadPoolMonitor102() {
        startMonitor();
    }


    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {   //复用执行第一个任务的线程
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":Hi");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

    }

    public void startMonitor() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    int queueSize = ((ThreadPoolExecutor) executor).getQueue().size();
                    int activeCount = ((ThreadPoolExecutor) executor).getActiveCount();
                    long completedTaskCount = ((ThreadPoolExecutor) executor).getCompletedTaskCount();
                    long taskCount = ((ThreadPoolExecutor) executor).getTaskCount();
                    // 总线程数（排队线程数 + 活动线程数 +  执行完成线程数）
//                    System.out.println("总线程数:" + taskCount);
                    System.out.println("排队线程数:" + queueSize
                            + " 活动线程数:" + activeCount
                            + " 执行完成线程数:" + completedTaskCount
                    );
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (completedTaskCount == 10) break;
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolMonitor102 monitor = new ThreadPoolMonitor102();
        monitor.test();

    }
}
