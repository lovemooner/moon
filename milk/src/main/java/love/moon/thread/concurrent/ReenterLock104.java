package love.moon.thread.concurrent;

/**
 * Author: lovemooner
 * Date: 2017/10/18 14:59
 */
public class ReenterLock104 {


    public static void main(String[] args) {
        final Counter counter = new Counter();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.decrease(20);
            }
        });
        t1.setName("decrease thread");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increase(10);
            }
        });
        t2.setName("increase thread");
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());

    }

    static class Counter {
        private int count = 10;

        public synchronized void increase(int num) {
            count += num;
            this.notifyAll();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void decrease(int num) {
            if (num > count) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count -= num;
        }
    }
}
