package love.moon.j2se.thread.concurrent.pool.mypool;

import java.util.concurrent.*;

public class MyExecuterPools extends ThreadPoolExecutor {
    public MyExecuterPools(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                           BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * 重写执行方法,线程池执行前进行调用
     */
    protected void afterExecute(Runnable r, Throwable t) {
        MyRunnable myr = (MyRunnable)r;
//        System.out.println(myr.getMyname()+"..执行完毕");
    }
    /**
     * 线程执行后进行调用
     */
    protected void beforeExecute(Thread t, Runnable r) {
        MyRunnable myr = (MyRunnable)r;
//        System.out.println(myr.getMyname()+"..准备执行");
    }
}