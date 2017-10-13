package love.moon.thread.volatiletest;

/**
 * Author: lovemooner
 * Date: 2017/9/5 14:32
 */
public class Volatile121 {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Volatile121 demo2 = new Volatile121();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        demo2.increase();
                }

                ;
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(demo2.inc);
    }
}
