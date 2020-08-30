package love.moon.j2se.thread.juc.future;

import love.moon.j2se.thread.juc.pool.monitor.ThreadPoolMonitor100;

import java.util.concurrent.*;

public class FutureCancelTask {


    public static void main(String[] args) {
        ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();
        Future<String> result=executor.submit(new Task());
        new Thread(() -> ThreadPoolMonitor100.startMonitor(executor,500L)).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result.cancel(true);
        System.out.printf("Main: Canceled: %s\n",result.isCancelled());
        System.out.printf("Main: Done: %s\n",result.isDone());
        executor.shutdown();
        System.out.printf("Main: The executor has finished\n");

    }




    public static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            while (true) {
//                System.out.printf("Task: Test\n");
                Thread.sleep(100);
            }
        }
    }

}

