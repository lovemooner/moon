package love.moon.thread.volatiletest;

/**
 * Author: lovemooner
 * Date: 2017/9/22 11:01
 */
public class Volatile122 extends Thread {

    private boolean flag = false;
//    private volatile boolean flag = false;
    private int i = 0;

    public void run() {
        while (!flag) {
            i++;
        }
        System.out.println("Thread Stop!");
    }

    public static void main(String[] args) throws Exception {
        Volatile122 vt = new Volatile122();
        vt.start();
        Thread.sleep(2000);
        vt.flag = true;
    }
}
