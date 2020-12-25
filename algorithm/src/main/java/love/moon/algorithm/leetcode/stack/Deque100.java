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
    public void myStack() {
        Deque<Integer> deque = new LinkedList<>();
        //入栈
        deque.push(3);
        deque.push(1);
        deque.push(7);
        //出栈操作
        while(deque.peek()!=null){
            System.out.println(deque.pop());
        }
        //loop
        for (Integer element : deque) {
            System.out.println(element);
        }

    }

}
