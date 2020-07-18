package love.moon.j2se.thread.concurrent.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Author: lovemooner
 * Date: 2017/5/11 11:23
 */
public class Semaphore100 {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semp = new Semaphore(5);
        for (int index = 0; index < 50; index++) {
            final int NO = index;
            exec.execute(() -> {
                try {
                    semp.acquire();// 获取许可
                    System.out.println(NO + " accessing");
                    Thread.sleep((long) (Math.random() * 6000));
                    semp.release();// 访问完后，释放
                    //availablePermits()指的是当前信号灯库中有多少个可以被使用
                    System.out.println("availablePermits: " + semp.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            exec.shutdown();
        }
    }
}
