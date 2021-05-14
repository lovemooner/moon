package love.moon.j2se.thread.juc.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;
import love.moon.common.HttpResponse;
import love.moon.j2se.thread.juc.pool.monitor.ThreadPoolMonitor100;
import love.moon.jdbc.JdbcOracleClient100;
import love.moon.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : ndong
 * date : 2021/5/13 14:42
 * desc :
 */
public class ThreadState100 {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(20, 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
//        new Thread(() -> ThreadPoolMonitor100.startMonitor(pool, 100L)).start();
        for (int i = 0; i < 1; i++) {
//        pool.execute(new IOThread());
            pool.execute(new ParallelStream());
//            pool.execute(new LockThread2());
        }
    }



    static class SocketIOThread implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            HttpResponse response= HttpUtil.sendGet("http://localhost:8092/galaxyappmanagement-server/init/hi");
            System.out.println(response.getContent());
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }


    static class SleepThread implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(30000l);
            //LockSupport.parkNanos(time)
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }

    static class ParallelStream implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            List<Integer> list=new ArrayList<>();
         for(int i=0;i<10;i++){
             list.add(i);
         }
         list.parallelStream().forEach(e->{
             HttpResponse response= HttpUtil.sendGet("http://localhost:8092/galaxyappmanagement-server/init/hi");
             System.out.println(e+""+response.getContent());
         });

        }

    }


    static class WaitThread implements Runnable {
        final Object lock = new Object();

        @SneakyThrows
        @Override
        public void run() {
            synchronized (lock) {
                lock.wait();
            }
        }

    }

    static class JdbcThread implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            JdbcOracleClient100 client = new JdbcOracleClient100();
            client.select();
        }

    }


    static class LockThread1 implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            synchronized (LockThread1.class) {
                while (true){}
            }
        }
    }


    private static final Lock lock = new ReentrantLock();

    static class LockThread2 implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "：run");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ": execute");
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        }
    }

}
