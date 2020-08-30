package love.moon.j2se.thread.juc.pool.mypool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        MyRunnable my = (MyRunnable)r;
//        System.out.println("拒绝执行...:"+my.getMyname());
    }

}
