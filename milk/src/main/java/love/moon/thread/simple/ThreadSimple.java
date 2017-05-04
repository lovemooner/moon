package love.moon.thread.simple;

/**
 * Author: lovemooner
 * Date: 2017/5/4 13:35
 */
public class ThreadSimple {

    public static void main(String[]args){
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                } catch (Throwable var2) {
                    ;
                }
            }
        });
        t.start();
    }
}
