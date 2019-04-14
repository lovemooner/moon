package love.moon.thread.native1.syncronized;

/**
 * Author: lovemooner
 * Date: 2017/10/18 14:05
 */
public class Synchronized101 {

    public synchronized void test() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    System.out.println("running");
                }
            }
        });
        t.start();
        t.interrupt();
        System.out.println("end========");
    }


    public static void main(String[] args) {
        Synchronized101 demo = new Synchronized101();
        demo.test();
    }

}
