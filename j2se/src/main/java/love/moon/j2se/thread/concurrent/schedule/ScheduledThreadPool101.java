package love.moon.j2se.thread.concurrent.schedule;


import java.util.concurrent.*;

public class ScheduledThreadPool101 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建大小为5的线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 1; i++) {
            Callable worker = () -> {
                String name= Thread.currentThread().getName();
                System.out.println("ThreadName: "+name+" start");
                try {
                    Thread.sleep(10000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadName: "+name+" end");
                return "good";
            };
            Future future= pool.submit(worker);
            // 周期性执行，每n秒执行一次
//            ScheduledFuture future= pool.scheduleAtFixedRate(worker, 0,1, TimeUnit.SECONDS);
            System.out.println("ready get");
            try {
                System.out.println("result:"+future.get(1,TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("end get");

        }

    }
}
