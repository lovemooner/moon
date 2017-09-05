package love.moon.thread;

/**
 *
 * 为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 * Author: lovemooner
 * Date: 2017/5/19 16:04
 */
public class ThreadLocal150 extends Thread{

    private static int sum=0;
    // 通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            return 0;
        }
    };

    public void run() {
        for (int i = 0; i < 3; i++) {
            // ④每个线程打出3个序列值
            System.out.println("thread[" + Thread.currentThread().getName() + "] --> num:"+ getNextNum());
        }
    }

    public int getNextNum() {
        System.out.println("getNextNum,this:"+this);
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }



    public static void main(String[] args) {

        ThreadLocal150 t1 = new ThreadLocal150();
        ThreadLocal150 t2 = new ThreadLocal150();
        ThreadLocal150 t3 = new ThreadLocal150();
        t1.start();
        t2.start();
        t3.start();
    }

}
