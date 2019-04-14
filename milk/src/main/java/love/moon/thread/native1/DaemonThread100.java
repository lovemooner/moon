package love.moon.thread.native1;

import java.util.concurrent.TimeUnit;

/**
 * Author: lovemooner
 * Date: 2017/6/28 16:44
 */
public class DaemonThread100 {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("I am working");
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.setDaemon(true);
        t1.start();
        System.out.println("end");

    }
}
