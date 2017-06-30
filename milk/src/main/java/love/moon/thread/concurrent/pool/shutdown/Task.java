package love.moon.thread.concurrent.pool.shutdown;

import java.util.concurrent.Callable;

/**
 * Author: lovemooner
 * Date: 2017/6/28 11:06
 */
public class Task implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("普通任务");
        return null;
    }
}
