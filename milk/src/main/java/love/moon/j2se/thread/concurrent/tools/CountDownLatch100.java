package love.moon.j2se.thread.concurrent.tools;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ndong on 2017/5/6.
 */
public class CountDownLatch100 {


    public static void main(String[] args) throws InterruptedException {
        int count = 2;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(1000L);
                    System.out.println("thread "+Thread.currentThread().getName()+" end.");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        latch.await();
        System.out.println("Main end");
    }



}

