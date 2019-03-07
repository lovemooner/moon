package love.moon.thread.pool;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueue100 {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put-thread: start");
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                }
                System.out.println("put-thread: end");
            }
        });

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take-thread: start");
                try {
                    Thread.sleep(2000L); //模式take操作的时间消耗
                    System.out.println("take-thread:take from putThread: " + queue.take());
                } catch (InterruptedException e) {
                }
                System.out.println("take-thread end");
            }
        });

        putThread.start();
        Thread.sleep(1000);
        takeThread.start();
    }
}