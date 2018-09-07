package love.moon.thread.object;

/**
 * Author: lovemooner
 * Date: 2017/9/15 13:54
 */
public class ThreadGroup100 implements Runnable {

    @Override
    public void run() {
//        System.out.println("Thread!");
        try {
            Thread.sleep(30000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getThreadGroup().getName());

        //put thread to group
        ThreadGroup100 tt = new ThreadGroup100();
        ThreadGroup group = new ThreadGroup("my-group");
        Thread thread = new Thread(group, tt);
        thread.start();
        System.out.println("ThreadGroup: " + thread.getThreadGroup().getName());
        //every group has it's parent except the system group
        System.out.println("group.getParent:"+group.getParent().getName());

        System.out.println("activeCount:"+Thread.activeCount());
    }

}
