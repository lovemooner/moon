package love.moon.j2se.thread.native1;

/**
 * Author: lovemooner
 * Date: 2017/5/4 13:35
 */
public class Thread100 {

    public static void main(String[]args){
        Thread t1 = new Thread(() -> {
            try {
            } catch (Throwable var2) {

            }
        });
        t1.start();

        Thread t2 = new Thread(new ThreadRunnable(),"test");
        t2.start();
    }


     static class ThreadRunnable implements Runnable{
        public void run() {
            System.out.println("I'm running!");
        }
    }
}
