package love.moon.algorithm.leetcode.stack;

import love.moon.util.Assert;

/**
 * @author : ndong
 * date : 2020/11/24 17:20
 * desc :
 */
public class MyStack {

    public static void main(String[] args) {
        MyStack stack=new MyStack();
        stack.push(1);
        stack.push(2);
        Assert.assertEqual(stack.top(),2);
        Assert.assertEqual(stack.pop(),2);
        Assert.assertFalse(stack.empty());

        MyStack stack1=new MyStack();
        stack1.push(1);
        Assert.assertEqual(stack1.pop(),1);
        Assert.assertTrue(stack1.empty());
    }

    private Node head ,tail;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        head = new Node(-1);
        tail = head;
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        tail.next = new Node(x,topNode());
        tail = tail.next;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        int val = tail.value;
        tail= tail.pre;
        tail.next=null;
        return val;
    }

    /**
     * Get the top element.
     */
    public int top() {
        return tail.value;
    }

    public Node topNode() {
        return tail;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
       return head.next==null;
    }


    class Node {
        private int value;
        private Node pre;
        private Node next;

        public Node() {
        }

        public Node(int value) {
          this.value=value;
        }

        public Node(int value,Node pre) {
            this.value = value;
            this.pre=pre;
        }
    }
}
