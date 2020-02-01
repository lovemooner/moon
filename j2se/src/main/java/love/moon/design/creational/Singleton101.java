package love.moon.design.creational;

import love.moon.jvm.Example;

/**
 * Author: lovemooner
 * Date: 2018/5/21 17:06
 */
public class Singleton101 {
    private static Singleton101 instance =new Singleton101();

    public static Singleton101 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton101 instance=  Singleton101.getInstance();
        Example example=new Example();
    }


}
