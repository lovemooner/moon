package love.moon.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: lovemooner
 * Date: 2017/6/30 15:51
 */
public class MaxThreadNumTest extends Thread{
    private static final AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) {
        while (true)
            (new MaxThreadNumTest()).start();
    }
    @Override
    public void run() {
        System.out.println(count.incrementAndGet());
        while (true)
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                break;
            }
    }
}
