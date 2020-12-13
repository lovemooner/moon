package love.moon.j2se.collection;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author : ndong
 * date : 2020/11/24 11:08
 * desc :
 */
public class Stack100 {


    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<>();
        System.out.println("stack: " + stack);
        stack.push(new Integer(42));
        stack.push(new Integer(66));
        stack.push(new Integer(99));
        stack.pop();
        stack.pop();
        System.out.println(stack.pop());
        System.out.println("stack: " + stack);
        try {
            System.out.println(stack.pop());
            System.out.println("stack: " + stack);
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
    }
}
