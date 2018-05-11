package love.moon.thread.concurrent.pool;

import java.util.concurrent.*;

/**
 * Author: lovemooner
 * Date: 2017/9/14 10:42
 */
public class ThreadPoolFutureAsynCall103 {

   static ExecutorService executor = Executors.newCachedThreadPool();

    public static synchronized String sendHttpGetRequest(String url) throws Exception {
        Future<String> future= executor.submit(new Callable<String>() {
            public String call() throws Exception{
                String result = "success";
                return result;
            }
        });
        return future.get();
    }

}
