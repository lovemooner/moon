package love.moon.thread.concurrent.pool;

import love.moon.thread.concurrent.pool.mypool.MyExecuterPools;
import love.moon.thread.concurrent.pool.mypool.MyRunnable;
import love.moon.thread.concurrent.pool.mypool.MyThreadFactory;
import love.moon.thread.concurrent.pool.mypool.RejectedExecutionHandlerImpl;

import java.util.concurrent.*;

public class SingleThreadExecutor102 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor1 = new MyExecuterPools(10, 10, 1,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new MyThreadFactory(), new RejectedExecutionHandlerImpl());
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for(int i=0;i<20;i++){
            executor.execute(new MyRunnable("n-"+i));
        }

        new Thread(()->{
            while (true){
                int activeCount = ((ThreadPoolExecutor) executor).getActiveCount();
                int queueSize = ((ThreadPoolExecutor) executor).getQueue().size();
                long completedTaskCount = ((ThreadPoolExecutor) executor).getCompletedTaskCount();
                long task = ((ThreadPoolExecutor) executor).getTaskCount();
                System.out.println("排队线程数:" + queueSize
                        + ",活动线程数:" + activeCount
                        + ",执行完成线程数:" + completedTaskCount)
                ;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
