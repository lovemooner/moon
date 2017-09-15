package love.moon.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Author: lovemooner
 * Date: 2017/9/14 10:42
 */
public class AsynCall1000 {

    public static synchronized String sendHttpGetRequest(final String url) throws Exception {
        FutureTask<String> task = new FutureTask<String>(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        String result = "success";
                        return result;
                    }
                });
        new Thread(task).start();
        return task.get();
    }

}
