package love.moon.j2se.thread.juc.readwritelock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author dongnan
 * @date 2020/8/11 11:18
 */
public class ReadWriteLock100 {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Object data = null;

    public void get() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to read data!");
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " have read data: " + data);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(Object data) {
        sleep(5000);
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to write data!");
            sleep(3000);
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " have write data: " + data);
        } finally {
            lock.writeLock().unlock();
        }
    }

    void sleep(long time){
        try {
            Thread.sleep((time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReadWriteLock100 lockTest=new ReadWriteLock100();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    lockTest.get();
                }
            }).start();
        }
        while (true) {
            lockTest.put(new Random().nextInt(10000));
        }
    }
}
