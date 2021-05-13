package love.moon.j2se.thread.juc.pool.monitor;

import lombok.extern.slf4j.Slf4j;
import love.moon.util.DateUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static love.moon.util.DateUtil.DATE_STRING_FORMAT_ALL;

/**
 * Author: lovemooner
 * Date: 2018/9/3 14:11
 */
@Slf4j
public class ThreadPoolMonitor100 {


    public static void configInfo(ExecutorService executor) {
        long keepAliveTime = ((ThreadPoolExecutor) executor).getKeepAliveTime(TimeUnit.MILLISECONDS);
        int poolSize = ((ThreadPoolExecutor) executor).getPoolSize();
        int getCorePoolSize = ((ThreadPoolExecutor) executor).getCorePoolSize();
        int maximumPoolSize = ((ThreadPoolExecutor) executor).getMaximumPoolSize();
        int largestPoolSize = ((ThreadPoolExecutor) executor).getLargestPoolSize();
    }


    public static void startMonitor(ExecutorService executor,long millis) {
       new Thread(()->{
               while (true){
                   //returns the approximate number of threads that are actively executing tasks
                   int activeCount = ((ThreadPoolExecutor) executor).getActiveCount();
                   int corePoolSize = ((ThreadPoolExecutor) executor).getCorePoolSize();
                   int queueSize = ((ThreadPoolExecutor) executor).getQueue().size();
                   long completedTaskCount = ((ThreadPoolExecutor) executor).getCompletedTaskCount();
                   long task = ((ThreadPoolExecutor) executor).getTaskCount();
                   String time=DateUtil.convertDateLongToDateString(DATE_STRING_FORMAT_ALL,System.currentTimeMillis());
                   System.out.println( time+",coreSize:" + corePoolSize
                           +",queueSize(排队):" + queueSize
                           + ",活动线程数(worker):" + activeCount
                           + ",执行完成线程数:" + completedTaskCount)
                   ;
                   try {
                       Thread.sleep(millis);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
       }).start();
    }

    public static void main(String[] args) {

    }

}
