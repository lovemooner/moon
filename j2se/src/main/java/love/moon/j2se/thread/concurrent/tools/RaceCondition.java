package love.moon.j2se.thread.concurrent.tools;

/**
 * Author: lovemooner
 * Date: 2017/9/18 16:33
 */
public class RaceCondition {
    private static boolean done;

    public static void main(final String[] args) throws InterruptedException {
        new Thread(new Runnable() {
                    public void run() {
                        int i = 0;
                        while (!done) {
                            i++;
                            System.out.println("i="+i);
                        }
                        System.out.println("Thread Done!");
                    }
                }
        ).start();
        System.out.println("OS: " + System.getProperty("os.name"));
        Thread.sleep(1000);
        done = true;
        System.out.println("flag done set to true");
    }
}
