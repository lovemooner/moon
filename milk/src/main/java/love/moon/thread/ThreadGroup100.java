package love.moon.thread;

/**
 * Author: lovemooner
 * Date: 2017/9/15 13:54
 */
public class ThreadGroup100 implements  Runnable{

    @Override
    public void run() {
        System.out.println("Thread!");
        try {
            Thread.sleep(30000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //put thread to group
        ThreadGroup100 tt=new ThreadGroup100();
        ThreadGroup group=new ThreadGroup("my-group");
        Thread thread=new Thread(group,tt);
        thread.start();
        System.out.println("ThreadGroup: "+thread.getThreadGroup().getName());
        //every group has it's parent except the system group
        System.out.println(group.getParent());
        System.out.println(Thread.currentThread().getThreadGroup().getName());

        System.out.println(Thread.activeCount());
    }

}