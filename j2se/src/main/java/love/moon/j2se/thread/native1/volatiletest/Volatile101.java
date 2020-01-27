package love.moon.j2se.thread.native1.volatiletest;

/**
 * Author: lovemooner
 * Date: 2018/5/29 14:09
 */
public class Volatile101 extends Thread{
    private  boolean isRunning = true;

    public void run() {
        while (isRunning) {
            System.out.println("running");
        }
    }

    public static void main(String[] args) throws Exception {
        Volatile101 volatile101=new Volatile101();
        Thread t =  new Thread(volatile101);
        t.start();
        Thread.sleep(500);
        volatile101.doStop();
    }

    public void doStop() {
        isRunning = false;
    }

}
