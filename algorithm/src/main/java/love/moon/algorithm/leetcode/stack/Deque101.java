package love.moon.algorithm.leetcode.stack;

import love.moon.util.Assert;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : ndong
 * date : 2020/11/24 18:11
 * desc :
 */
public class Deque101 {

    public static void main(String[] args) {
        Deque101 stack=new Deque101();
        stack.push(1);
        stack.push(2);
        Assert.assertEqual(stack.top(),2);
        Assert.assertEqual(stack.pop(),2);
        Assert.assertFalse(stack.empty());

        Deque101 stack1=new Deque101();
        stack1.push(1);
        Assert.assertEqual(stack1.pop(),1);
        Assert.assertTrue(stack1.empty());
    }

    private Deque<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public Deque101() {
        queue=new LinkedList();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.addFirst(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue.remove();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue.element();
    }


    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.size()==0;
    }

}
