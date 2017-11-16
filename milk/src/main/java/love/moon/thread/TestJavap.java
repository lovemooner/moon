package love.moon.thread;

/**
 * Author: lovemooner
 * Date: 2017/11/1 15:57
 */
public class TestJavap {
    //    static int i = 888;
    static volatile int i = 888;

    public static void main(String[] args) {
        test();
        "abc".replace("aa","bb");

    }

    public static void test() {
        i++;
        int y = i;
        System.out.println(y);
    }
}