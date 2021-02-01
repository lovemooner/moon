package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

import java.util.Deque;
import java.util.LinkedList;

public class KhisValid {

    public static void main(String[] args) {
        KhisValid sol=new KhisValid();
        Assert.assertFalse(sol.isValid("([)]"));
        Assert.assertTrue(sol.isValid("()"));
    }

    public  boolean isValid(String s) {
        Deque<Character> q=new LinkedList<>();
        for(int i=0;i<s.length();i++){
            if(q.peek()!=null&& getRes(q.peek())==null){
                return false;
            }
            if(q.peek()!=null&& getRes(q.peek())==s.charAt(i)){
                q.pop();
            }else{
                q.push(s.charAt(i));
            }
        }
        return q.size()==0;
    }

    public  Character getRes(Character c){
        if(c=='(')return ')';
        else if (c=='{')return '}';
        else if (c=='[')return ']';
        return null;
    }

}
