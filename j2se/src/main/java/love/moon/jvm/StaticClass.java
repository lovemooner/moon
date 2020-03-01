package love.moon.jvm;

/**
 * Author: lovemooner
 * Date: 2018/1/11 16:06
 */
public class StaticClass {
    public static final String NAME = "我是常量";
//    public static int m = 33;
    static{
        System.out.println("StaticClass被初始化");
    }

    public static void main(String[] args) {
        System.out.println(StaticClass.NAME);
//        System.out.println(StaticClass.m);
    }
}
