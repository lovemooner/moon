package love.moon.keyWord;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: lovemooner
 * Date: 2018/5/22 14:25
 */
public class Singleton {
    private static final Singleton SINGLETON = new Singleton();
    private Singleton singleton=new Singleton();
    private static String str = "a";

    public Singleton() {
        System.out.println(str);
    }

    public static Singleton getInstance() {
        return SINGLETON;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}