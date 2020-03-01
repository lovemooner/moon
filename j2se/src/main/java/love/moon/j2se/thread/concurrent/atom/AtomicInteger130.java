package love.moon.j2se.thread.concurrent.atom;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: lovemooner
 * Date: 2017/6/14 16:54
 */
public class AtomicInteger130 {
    public AtomicInteger inc = new AtomicInteger();

    public void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) {
        final AtomicInteger130 test = new AtomicInteger130();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                }
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);




    }
}
