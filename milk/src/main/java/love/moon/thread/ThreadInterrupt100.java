package love.moon.thread;

/**
 * Author: lovemooner
 * Date: 2017/9/15 14:39
 */
public class ThreadInterrupt100 implements  Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(30000l);
            System.out.println("here");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //put thread to group
        ThreadInterrupt100 tt=new ThreadInterrupt100();
        Thread thread=new Thread(tt);
        thread.start();
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

}

