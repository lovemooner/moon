package love.moon.thread.native1.syncronized;

/**
 * Author: lovemooner
 * Date: 2018/5/29 14:16
 */
public class Synchronized100 {
    Object lock = new Object();
    private int count = 0;

    public synchronized void increase() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        final Synchronized100 synchronized100 = new Synchronized100();
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized100.increase();
                }
            }).start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(synchronized100.getCount());
    }


}
