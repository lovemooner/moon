package love.moon.j2se.thread.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: lovemooner
 * Date: 2017/6/29 14:01
 */
public class ThreadPoolShutdown102 {

    public static void main(String[] args) throws InterruptedException {
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
        final ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(t1);
        System.out.println("end");
        while (true){
            Thread.sleep(1000L);
            pool.shutdown();
        }
    }
}
