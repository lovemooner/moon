package love.moon.thread.native1;

/**
 * Author: lovemooner
 * Date: 2017/9/15 14:39
 */
public class ThreadInterrupt100 implements  Runnable{

    @Override
    public void run() {
//            Thread.sleep(30000l);
        while (true)
            System.out.println("here");

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

