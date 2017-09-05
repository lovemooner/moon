package love.moon.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Author: lovemooner
 * Date: 2017/6/30 17:36
 */
public class NativeOutOfMemoryError160 {

    public static void main(String[] args) {

        for (int i = 0; ; i++) {
            System.out.println("i = " + i);
            new Thread(new HoldThread()).start();
        }
    }

}

 class HoldThread extends Thread {
    CountDownLatch cdl = new CountDownLatch(1);

    public HoldThread() {
        this.setDaemon(true);
    }

    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
        }
    }
}
