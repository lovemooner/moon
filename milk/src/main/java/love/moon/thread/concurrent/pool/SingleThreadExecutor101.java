package love.moon.thread.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor101 {

    static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": thread index-" + index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        ThreadPoolMonitor100.startMonitor(executor, 1000l);

    }
}
