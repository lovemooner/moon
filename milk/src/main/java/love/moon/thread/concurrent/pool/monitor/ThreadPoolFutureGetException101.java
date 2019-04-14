package love.moon.thread.concurrent.pool.monitor;

import java.util.concurrent.*;

/**
 * Author: lovemooner
 * Date: 2017/10/16 15:30
 */
public class ThreadPoolFutureGetException101 {

    public void testExecute() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {   //复用执行第一个任务的线程
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    if (1 == 1) throw new RuntimeException("exception in submit!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void testSubmit() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                if (1 == 1) throw new RuntimeException("exception in submit!");
                return "Hi";
            }
        });
        System.out.println(future.get());
        System.out.println("==end==");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {


    }
}
