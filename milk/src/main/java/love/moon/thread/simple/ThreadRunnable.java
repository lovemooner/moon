package love.moon.thread.simple;

/**
 * Author: lovemooner
 * Date: 2017/5/3 14:42
 */
public class ThreadRunnable implements Runnable{
    public void run() {
        System.out.println("I'm running!");
    }

    public static void main(String[]args){
        ThreadRunnable tt = new ThreadRunnable();
        Thread t = new Thread(tt,"test");
        t.start();
    }
}
