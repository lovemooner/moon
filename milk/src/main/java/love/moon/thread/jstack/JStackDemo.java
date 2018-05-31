package love.moon.thread.jstack;

/**
 * Author: lovemooner
 * Date: 2018/5/31 16:54
 */
public class JStackDemo {


    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    static class DeadLockClass implements Runnable {
        public boolean falg;// 控制线程

        DeadLockClass(boolean falg) {
            this.falg = falg;
        }

        public void run() {
            if (falg) {
                while (true) {
                    synchronized (JStackDemo.lock1) {
                        System.out.println("get lock1 " + Thread.currentThread().getName());

                        synchronized (JStackDemo.lock2) {
                            System.out.println("get lock2 " + Thread.currentThread().getName());
                        }
                    }
                }
            } else {
                while (true) {
                    synchronized (JStackDemo.lock2) {
                        System.out.println("get lock2 " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (JStackDemo.lock1) {
                            System.out.println("get lock1 " + Thread.currentThread().getName());
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockClass(true));//建立一个线程
        Thread t2 = new Thread(new DeadLockClass(false));//建立另一个线程
        t1.start();//启动一个线程
        t2.start();//启动另一个线程
    }
}


