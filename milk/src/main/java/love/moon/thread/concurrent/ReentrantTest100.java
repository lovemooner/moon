package love.moon.thread.concurrent;

/**
 * Author: lovemooner
 * Date: 2017/9/19 17:03
 */
public class ReentrantTest100 {
    public void method1() {
        synchronized (ReentrantTest100.class) {
            System.out.println("method1");
            method2();
        }
    }
    public void method2() {
        synchronized (ReentrantTest100.class) {
            System.out.println("method2");
        }
    }
    public static void main(String[] args) {
        new ReentrantTest100().method1();
    }
}