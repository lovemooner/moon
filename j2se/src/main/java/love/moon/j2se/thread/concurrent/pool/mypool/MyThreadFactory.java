package love.moon.j2se.thread.concurrent.pool.mypool;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread td = new Thread(r);
        td.setName("myfactory-"+td.getId());
        td.setUncaughtExceptionHandler((thread, throwable) -> {
            //自定义异常捕获机制..
            System.out.println("thread execute error");
        });
        return td;
    }

}