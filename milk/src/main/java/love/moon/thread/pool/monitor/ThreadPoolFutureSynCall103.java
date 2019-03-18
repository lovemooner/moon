package love.moon.thread.pool.monitor;

import java.util.concurrent.*;

/**
 * Author: lovemooner
 * Date: 2017/9/14 10:42
 */
public class ThreadPoolFutureSynCall103 {

   static ExecutorService executor = Executors.newCachedThreadPool();

    public static synchronized String sendHttpGetRequest(String url) throws Exception {
        Future<String> future= executor.submit(() -> {
            String result = "success";
            return result;
        });
        return future.get();
    }

}
