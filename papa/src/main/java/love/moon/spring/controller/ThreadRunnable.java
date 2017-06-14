package love.moon.spring.controller;

/**
 * Author: lovemooner
 * Date: 2017/6/13 17:16
 */
public class ThreadRunnable implements Runnable{
    public void run() {
        while (true){
            System.out.println("I'm running! "+Thread.currentThread().getName());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
