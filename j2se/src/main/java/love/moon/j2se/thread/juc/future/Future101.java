package love.moon.j2se.thread.juc.future;

import java.util.concurrent.*;

/**
 * Author: lovemooner
 * Date: 2017/6/28 13:13
 */
public class Future101 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<String> future = executor.submit(() -> {
            if(1==1){
                for(;;){
//                    System.out.println("running");
                }
            }
            return "success";
        });
        Thread.sleep(1000l);
        boolean result = future.cancel(true);
        if (result) {
            System.out.println("success");
            int activeCount = ((ThreadPoolExecutor) executor).getActiveCount();
            executor.shutdownNow();
            Thread.sleep(1000l);
            System.out.println("activeCount: "+activeCount);
            System.out.println("isCancelled: "+future.isCancelled());
        }


    }


}
