package love.moon.thread.asyn;

/**
 *
 * 为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 * Author: lovemooner
 * Date: 2017/5/19 16:04
 */
public class ThreadLocalDemo extends Thread{

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
            System.out.println("thread[" + Thread.currentThread().getName() + "] --> num:"
                    + getNextNum() +",sum:"+getNextSum());
        }
    }

    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public int getNextSum() {
       return ++sum;
    }

    public static void main(String[] args) {
        ThreadLocalDemo t1 = new ThreadLocalDemo();
        ThreadLocalDemo t2 = new ThreadLocalDemo();
        ThreadLocalDemo t3 = new ThreadLocalDemo();
        t1.start();
        t2.start();
        t3.start();
    }

}
