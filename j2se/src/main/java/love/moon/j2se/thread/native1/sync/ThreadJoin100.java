package love.moon.j2se.thread.native1.sync;

/**
 * @author lovemooner
 */
public class ThreadJoin100 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " working");
            }
        });
        thread.start();
        thread.join();
        System.out.println("main end");
    }


}
