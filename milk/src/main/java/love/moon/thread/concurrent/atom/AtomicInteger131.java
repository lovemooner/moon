package love.moon.thread.concurrent.atom;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: lovemooner
 * Date: 2017/10/11 13:51
 */
public class AtomicInteger131 {

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        System.out.println(ai.addAndGet(5));
        System.out.println(ai.getAndAdd(1));
        System.out.println(ai.get()); //6
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.incrementAndGet());
    }
}
