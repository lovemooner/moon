package love.moon.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Author: lovemooner
 * Date: 2017/6/28 13:13
 */
public class CallableFuture {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> fList = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("id:" + finalI + ",calling");
                    //一个模拟耗时的操作
                    TimeUnit.SECONDS.sleep(3);
                    return "id:" + finalI + " return ";
                }
            });
            fList.add(future);
        }
        executorService.shutdown();
        //遍历任务的结果
        for (Future<String> fs : fList) {
            try {
                System.out.println(fs.get());     //打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}
