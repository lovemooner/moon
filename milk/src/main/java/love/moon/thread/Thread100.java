package love.moon.thread;

/**
 * Author: lovemooner
 * Date: 2017/9/15 14:39
 */
public class Thread100 implements  Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(30000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //put thread to group
        Thread100 tt=new Thread100();
        Thread thread=new Thread(tt);
        thread.start();
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

}

