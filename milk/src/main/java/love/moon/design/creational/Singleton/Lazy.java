package love.moon.design.creational.Singleton;

/**
 *
 * 因为在任何时候只能有一个线程调用 getInstance() 方法。但是同步操作只需要在第一次调用时才被需要，即第一次创建单例实例对象时。
 * Author: lovemooner
 * Date: 2017/6/14 10:59
 */
public class Lazy {

    private static Lazy instance;

    private Lazy() {
    }

    public static synchronized Lazy getInstance() {
        if (instance == null) {
            instance = new Lazy();
        }
        return instance;
    }
}
