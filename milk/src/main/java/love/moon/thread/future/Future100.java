package love.moon.thread.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Author: lovemooner
 * Date: 2017/6/28 13:13
 */
public class Future100 {

    public static void main(String[] args)  {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("一些很耗时的操作(不能占用我们的调用线程时间的");
                return "success";
            }
        });
        //在我们异步操作的同时一样可以做其他操作
        doSomethingElse();
        String result = null;
        try {
            result = future.get();
            if("success".equals(result)){
                System.out.println("success");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }

    private static void doSomethingElse()   {
        System.out.println("doSomethingElse");
    }
}
