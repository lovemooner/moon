package love.moon.thread.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: lovemooner
 * Date: 2017/5/10 14:07
 */
public class ThreadPool {

    public static void main(String[] args) {
        Executor cachedThreadPool = Executors.newCachedThreadPool();
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
    }
}
