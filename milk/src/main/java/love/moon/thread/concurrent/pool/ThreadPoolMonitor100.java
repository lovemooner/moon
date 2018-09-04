package love.moon.thread.concurrent.pool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: lovemooner
 * Date: 2018/9/3 14:11
 */
public class ThreadPoolMonitor100 {


    public static void configInfo(ExecutorService executor) {
        long keepAliveTime = ((ThreadPoolExecutor) executor).getKeepAliveTime(TimeUnit.MILLISECONDS);
        int poolSize = ((ThreadPoolExecutor) executor).getPoolSize();
        int getCorePoolSize = ((ThreadPoolExecutor) executor).getCorePoolSize();
        int maximumPoolSize = ((ThreadPoolExecutor) executor).getMaximumPoolSize();
        int largestPoolSize = ((ThreadPoolExecutor) executor).getLargestPoolSize();
    }


    public static void status(ExecutorService executor) {


        int activeCount = ((ThreadPoolExecutor) executor).getActiveCount();
        int queueSize = ((ThreadPoolExecutor) executor).getQueue().size();
        long completedTaskCount = ((ThreadPoolExecutor) executor).getCompletedTaskCount();
        long task = ((ThreadPoolExecutor) executor).getTaskCount();
        System.out.println("排队线程数:" + queueSize
                + ",活动线程数:" + activeCount
                + ",执行完成线程数:" + completedTaskCount)
        ;
    }

    public static void main(String[] args) {

    }

}
