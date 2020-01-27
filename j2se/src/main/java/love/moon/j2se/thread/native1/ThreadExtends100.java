package love.moon.j2se.thread.native1;

/**
 * Created by lovemooner on 2017/5/3.
 */
public class ThreadExtends100 extends Thread {
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
        ThreadExtends100 t=new ThreadExtends100();
        t.start();
        ThreadExtends100 t2=new ThreadExtends100();
        t2.start();



    }
}
