package love.moon.thread.concurrent.pool.mypool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyExecuterPoolsTest {

    public static void main(String[] args) {
        MyExecuterPools executor = new MyExecuterPools(1, 2, 1,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new MyThreadFactory(), new RejectedExecutionHandlerImpl());
        for(int i=0;i<20;i++){
            executor.execute(new MyRunnable("n-"+i));
        }

        new Thread(()->{
            while (true){
                int activeCount = executor.getActiveCount();
                int queueSize = executor.getQueue().size();
                long completedTaskCount = executor.getCompletedTaskCount();
                long task = executor.getTaskCount();
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
