package love.moon.thread.volatiletest;

/**
 * Author: lovemooner
 * Date: 2018/5/29 13:48
 */
public class Volatile100 extends Thread {

    private volatile boolean isRunning = true;
//    private int count = 0;

    public void doStop() {
        isRunning = false;
    }

    public void run() {
        while (isRunning) {
//            count++;
        }
//        System.out.println("count=" + count);
    }

    public static void main(String[] args) throws Exception {
        Volatile100 volatile100=new Volatile100();
        Thread t =  new Thread(volatile100);
        t.start();
        Thread.sleep(500);
        volatile100.doStop();
    }

}
