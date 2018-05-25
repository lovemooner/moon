package love.moon.jvm;

/**
 * Author: lovemooner
 * Date: 2018/1/9 16:00
 */
public class Example {
    static int size1 = 6;

    public Example() {
        System.out.println("Example");
    }

    public Example(String arg) {
        System.out.println("Example2");
    }

    static {
//        System.out.println("static");

    }

    public static void main(String[] args) {
        Example example = new Example();
    }
}
