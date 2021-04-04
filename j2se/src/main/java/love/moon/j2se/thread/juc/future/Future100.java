package love.moon.j2se.thread.juc.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : lovemooner
 * @date: 2017/6/28 13:13
 */
public class Future100 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            System.out.println("一些很耗时的操作(不能占用我们的调用线程时间的");
            Thread.sleep(10000);
            return "success";
        });
        //在我们异步操作的同时一样可以做其他操作
        doSomethingElse();
        String result = null;
        result = future.get();
        if ("success".equals(result)) {
            System.out.println("success");
        }
        System.out.println("end");

    }

    private static void doSomethingElse() {
        System.out.println("doSomethingElse");
    }
}
