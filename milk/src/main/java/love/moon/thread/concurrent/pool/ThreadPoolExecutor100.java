package love.moon.thread.concurrent.pool;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: lovemooner
 * Date: 2018/8/17 17:10
 */
public class ThreadPoolExecutor100 {


    public void test() {
        // 构造一个线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 1; i <= 10; i++) {
            try {
                // 产生一个任务，并将其加入到线程池
                String task = "task@ " + i;
                System.out.println("put task@" + task);
                threadPool.execute(new ThreadPoolTask(task));

                // 便于观察，等待一段时间
                Thread.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor100 executor100 = new ThreadPoolExecutor100();
        executor100.test();
    }

    public class ThreadPoolTask implements Runnable, Serializable {
        // 保存任务所需要的数据
        private Object threadPoolTaskData;

        ThreadPoolTask(Object tasks) {
            this.threadPoolTaskData = tasks;
        }

        public void run() {
            // 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
//            System.out.println(Thread.currentThread().getName());
            System.out.println("start .." + threadPoolTaskData);

            try {
                // //便于观察，等待一段时间
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("end .." + threadPoolTaskData);
            threadPoolTaskData = null;

        }

        public Object getTask() {
            return this.threadPoolTaskData;
        }
    }
}