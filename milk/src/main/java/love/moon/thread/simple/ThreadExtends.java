package love.moon.thread.simple;

/**
 * Created by lovemooner on 2017/5/3.
 */
public class ThreadExtends extends Thread {
    private int ticket = 10;

    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() +
                        " ticket:" + ticket--);
            } else {
                   break;
            }
        }
    }

    public static void main(String[]args){
        ThreadExtends t=new ThreadExtends();
        t.start();
        ThreadExtends t2=new ThreadExtends();
        t2.start();
    }
}
