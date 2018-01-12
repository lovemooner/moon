package love.moon.jvm;

/**
 * Author: lovemooner
 * Date: 2018/1/9 17:19
 */
public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton singleton = Singleton.getInstance();
                    singleton.test();
                }
            }).start();
        }

    }
}
