package love.moon.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor100 {

    public static void main(String[] args) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 200; i++) {
            final int index = i;
            newSingleThreadExecutor.execute(new ThreadPoolUtil(index));
        }
    }

    public static class ThreadPoolUtil implements Runnable {

        private Integer index;

        public ThreadPoolUtil(Integer index) {
            this.index = index;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+": 线程标识是"+index);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
