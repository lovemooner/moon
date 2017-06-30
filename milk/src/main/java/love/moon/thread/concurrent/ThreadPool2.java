package love.moon.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: lovemooner
 * Date: 2017/6/29 14:01
 */
public class ThreadPool2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setDaemon(true);
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(t1);
        System.out.println("end");
        while (true){
            Thread.sleep(1000L);
            cachedThreadPool.shutdown();
        }
    }
}
