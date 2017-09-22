package love.moon.thread.reentrant;

/**
 * Author: lovemooner
 * Date: 2017/9/19 17:18
 */
public class ReentrantTest103 {

    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("A get lock");
                        Thread.sleep(30000);
                        System.out.println("A end");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("B get lock");
                }
            }
        }).start();
    }
}
