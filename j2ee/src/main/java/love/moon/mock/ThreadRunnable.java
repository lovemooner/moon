package love.moon.mock;

/**
 * Author: lovemooner
 * Date: 2017/6/13 17:16
 */
public class ThreadRunnable implements Runnable{
    public void run() {
            System.out.println("Create thread: "+Thread.currentThread().getName());
            try {
                Thread.sleep(3600000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
