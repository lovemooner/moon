package love.moon.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lovemooner
 * Date: 2017/10/18 14:05
 */
public class Synchronized100 {

    public synchronized void test() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    System.out.println("running");
                }
            }
        });
        t.start();
        t.interrupt();
        System.out.println("end========");
    }


    public static void main(String[] args) {
        Synchronized100 demo = new Synchronized100();
        demo.test();
    }

}
