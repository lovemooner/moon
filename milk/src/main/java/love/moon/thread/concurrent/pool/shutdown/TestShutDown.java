package love.moon.thread.concurrent.pool.shutdown;

import java.util.concurrent.*;

/**
 * Author: lovemooner
 * Date: 2017/6/28 11:09
 */
public class TestShutDown {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        service.submit(new Task());
        service.submit(new Task());
        Future<String> fs=  service.submit(new LongTask());
        service.submit(new Task());

        System.out.println(fs.get());
        service.shutdown();

        while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程池没有关闭");
        }

        System.out.println("线程池已经关闭");
    }
}
