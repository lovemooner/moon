package love.moon.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Author: lovemooner
 * Date: 2017/6/28 13:13
 */
public class CallableFuture103 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("id:" + finalI + ",calling");
                    //一个模拟耗时的操作
                    TimeUnit.SECONDS.sleep(1);
                    return "id:" + finalI + " return ";
                }
            });
            futures.add(future);
        }
//        executorService.shutdown();
        //遍历任务的结果
        for (Future<String> fs : futures) {
            try {
                //Waits if necessary for the computation to complete, and then retrieves its result.
                System.out.println(fs.get());
                System.out.println("--");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}
