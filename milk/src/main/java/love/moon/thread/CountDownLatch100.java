package love.moon.thread;

import love.moon.thread.queue.ConcurrentLinkedQueue100;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

