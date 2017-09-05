package love.moon.thread.concurrent.pool.shutdown;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Author: lovemooner
 * Date: 2017/6/28 11:07
 */
public class LongTask implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("长时间任务");
        TimeUnit.SECONDS.sleep(5);
        return "return long task";
    }
}
