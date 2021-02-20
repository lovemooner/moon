package love.moon.algorithm.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : ndong
 * date : 2021/2/15 22:42
 * desc :
 */
public class MinStack155 {

    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public MinStack155() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
