package love.moon.design.Singleton;

/**
 * Author: lovemooner
 * Date: 2017/6/14 14:05
 */
public class Hungry {
    //类加载时就初始化
    private static final Hungry instance = new Hungry();

    private Hungry() {
    }

    public static Hungry getInstance() {
        return instance;
    }
}
