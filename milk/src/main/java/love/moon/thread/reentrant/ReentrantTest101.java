package love.moon.thread.reentrant;

/**
 * Author: lovemooner
 * Date: 2017/9/19 17:04
 */
public class ReentrantTest101 {
    public synchronized void method1() {
        System.out.println("method1");
        method2();
    }

    public synchronized void method2() {
        System.out.println("method2");
    }

    public static void main(String[] args) {
        new ReentrantTest100().method1();
    }
}
