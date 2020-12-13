package love.moon.j2se.thread.juc.ReentrantLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : ndong
 * date : 2020/11/26 11:15
 * desc :
 */
public class Condition100 {
    private Queue<String> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void addTask(String s) {
        lock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
