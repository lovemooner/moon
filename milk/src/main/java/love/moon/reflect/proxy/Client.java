package love.moon.reflect.proxy;

/**
 * Author: lovemooner
 * Date: 2017/5/8 13:57
 */
public class Client {

    public static void main(String[] args) {
        Subject proxy = DynProxyFactory.getInstance();
        proxy.hello("world");
    }
}
