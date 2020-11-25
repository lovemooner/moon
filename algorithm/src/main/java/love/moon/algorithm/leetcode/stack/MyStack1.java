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
public class MyStack1 {

    public static void main(String[] args) {
        MyStack1 stack=new MyStack1();
        stack.push(1);
        stack.push(2);
        Assert.assertEqual(stack.top(),2);
        Assert.assertEqual(stack.pop(),2);
        Assert.assertFalse(stack.empty());

        MyStack1 stack1=new MyStack1();
        stack1.push(1);
        Assert.assertEqual(stack1.pop(),1);
        Assert.assertTrue(stack1.empty());
    }

    private Deque<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public MyStack1() {
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
