package love.moon.algorithm.leetcode.stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : ndong
 * date : 2020/12/22 11:09
 * desc :
 */
public class Deque100 {


    @Test
    public void stack1() {
        Deque<Integer> deque = new LinkedList<>();
        deque.push(3);
        deque.push(1);
        deque.push(7);
        System.out.println("loop");
        for (Integer element : deque) {
            System.out.println(element);
        }
        System.out.println("出栈操作");
//        while (deque.peek() != null) {
        while (!deque.isEmpty()) {
            System.out.println(deque.pop());
        }
    }

    @Test
    public void stack2() {
        Deque<Integer> deque = new LinkedList<>();
        deque.push(-2);
        deque.push(-3);
        deque.push(0);
        System.out.println(deque.peek());
    }

}
