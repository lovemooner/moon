package love.moon.j2se.thread.juc.schedule;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool100 {

    public static void main(String[] args) throws InterruptedException {
        // 创建大小为5的线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 1; i++) {
            Runnable worker = () -> {
                String name= Thread.currentThread().getName();
                System.out.println("ThreadName: "+name+" start");
                try {
                    Thread.sleep(10000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadName: "+name+" end");
            };
            // 只执行一次
//          scheduledThreadPool.schedule(worker, 5, TimeUnit.SECONDS);
            // 周期性执行，每n秒执行一次
            pool.scheduleAtFixedRate(worker, 0,1, TimeUnit.SECONDS);
        }

//        Thread.sleep(10000);
//
//        System.out.println("Shutting down executor...");
//        // 关闭线程池
//        pool.shutdown();
//        boolean isDone;
//        // 等待线程池终止
//        do {
//            isDone = pool.awaitTermination(1, TimeUnit.DAYS);
//            System.out.println("awaitTermination...");
//        } while(!isDone);
//
//        System.out.println("Finished all threads");
    }


}
