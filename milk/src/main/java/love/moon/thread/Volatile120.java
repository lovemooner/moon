package love.moon.thread;

/**
 * Author: lovemooner
 * Date: 2017/6/15 16:34
 */
public class Volatile120 {


   private volatile boolean isRunning = false;

    public void stop() {
        isRunning = false;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    System.out.println("someOperation");
                }
            }
        }).start();
    }



}
