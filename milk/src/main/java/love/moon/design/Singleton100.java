package love.moon.design;

/**
 * Author: lovemooner
 * Date: 2018/5/21 16:25
 */
public class Singleton100 {
    private volatile static Singleton100 instance ;

    public static Singleton100 getInstance() {
        if (instance == null) {
            synchronized (Singleton100.class) {
                if (instance == null) {
                    instance = new Singleton100();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton100.getInstance();//触发<clinit>
    }
}
