package love.moon.thread.pool;

import love.moon.thread.pool.monitor.ThreadPoolMonitor100;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * Author: lovemooner
 * Date: 2018/8/17 17:10
 */
public class ThreadPoolExecutor101 {

    public static void main(String[] args) {
        ThreadPoolExecutor101 executor100 = new ThreadPoolExecutor101();
        executor100.test();
    }


    public void test() {
        // 构造一个线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 40, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        new Thread(() -> ThreadPoolMonitor100.startMonitor(pool,100L)).start();

        for (int i = 1; i <= 100; i++) {
            try {
                // 产生一个任务，并将其加入到线程池
                String name = "task@ " + i;
                System.out.println("submit "+name);
                pool.submit(new ThreadPoolTask(name));

                // 便于观察，等待一段时间
                Thread.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public class ThreadPoolTask implements Runnable, Serializable {
        // 保存任务所需要的数据
        private Object data;

        ThreadPoolTask(Object data) {
            this.data = data;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName()+" begin .." + data);

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("end .." + data);
            data = null;

        }

        public Object getTask() {
            return this.data;
        }
    }
}