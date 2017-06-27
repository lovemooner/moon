package love.moon.thread.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: lovemooner
 * Date: 2017/5/10 14:07
 */
public class ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            pool.execute(new Runnable() {   //复用执行第一个任务的线程
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":Hi");
                }
            });
        }
    }
}
