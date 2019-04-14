package love.moon.thread.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuture100 {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(()->{
            completableFuture.complete(Thread.currentThread().getName());
        }).start();
        doSomethingElse();//做你想做的其他操作

        try {
            System.out.println(completableFuture.get());
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
