package love.moon.j2se.thread.juc.queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Author: lovemooner
 * Date: 2018/5/11 10:33
 */

public class BlockingQueue100 {

    public static void main(String[] args) {
        final BlockingQueue queue = new ArrayBlockingQueue(3);
        for(int i=0;i<2;i++){
            new Thread(() -> {
                while(true){
                    try {
                        Thread.sleep((long)(Math.random()*1000));
                        System.out.println(Thread.currentThread().getName() + "准备放数据!");
                        queue.put(1);
                        System.out.println(Thread.currentThread().getName() + "已经放了数据，" +
                                "队列目前有" + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

        new Thread(() -> {
            while(true){
                try {
                    //将此处的睡眠时间分别改为100和1000，观察运行结果
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "准备取数据!");
                    queue.take();
                    System.out.println(Thread.currentThread().getName() + "已经取走数据，" +
                            "队列目前有" + queue.size() + "个数据");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}