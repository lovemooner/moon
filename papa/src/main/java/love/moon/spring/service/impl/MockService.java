package love.moon.spring.service.impl;

import love.moon.spring.service.IMockService;
import love.moon.util.HttpUtil;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MockService implements IMockService {

    ExecutorService executor = Executors.newCachedThreadPool();


    public void mockBlockRequest2(int loop){
        final String url = "http://192.168.1.107:8001/test";
        for (int i = 0; i < loop; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String result = HttpUtil.sendGet(url);
                    System.out.println(result);
                }
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (((ThreadPoolExecutor) executor).getActiveCount() > 0) {
                    int activeCount = ((ThreadPoolExecutor) executor).getActiveCount();
                    int queueSize = ((ThreadPoolExecutor) executor).getQueue().size();
                    long completedTaskCount = ((ThreadPoolExecutor) executor).getCompletedTaskCount();
                    long task = ((ThreadPoolExecutor) executor).getTaskCount();
                    System.out.println("排队线程数:" + queueSize
                            + ",活动线程数:" + activeCount
                            + ",执行完成线程数:" + completedTaskCount);
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void maxThreadNum() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=========== MemoryAgent ===========");
                Long max = Runtime.getRuntime().maxMemory();
                Long total = Runtime.getRuntime().totalMemory();
                Long free = Runtime.getRuntime().freeMemory();
                System.out.println("=========== MAX :"+max);
                System.out.println("=========== TOTAL:"+total);
                System.out.println("=========== FREE :"+free);
            }
        }).start();

         final AtomicInteger count = new AtomicInteger();
        while (true){
            (new MaxThreadNum(count)).start();
            try {
                Thread.sleep(10l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public class MaxThreadNum extends Thread{
        private AtomicInteger count;

        public MaxThreadNum(AtomicInteger count){
            this.count=count;
        }
        @Override
        public void run() {
            System.out.println("Current Thread Num:"+count.incrementAndGet());
            while (true)
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    break;
                }
        }
    }
}
