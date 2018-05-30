package love.moon.thread.volatiletest;

/**
 * Author: lovemooner
 * Date: 2017/6/15 16:34
 */
public class Volatile120 {


    private volatile boolean isRunning = true;
    private int count = 0;

    public void stop() {
        isRunning = false;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    count++;
                }
                System.out.println("count=" + count);
            }
        }).start();
    }

    public static void main(String[] args) throws Exception {
        Volatile120 vt = new Volatile120();
        vt.start();
        Thread.sleep(500);
        vt.stop();
    }


}
