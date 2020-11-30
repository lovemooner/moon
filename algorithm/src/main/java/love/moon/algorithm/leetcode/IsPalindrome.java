package love.moon.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class IsPalindrome {

    public static boolean isPalindrome(int x) {
        if(x<0) return false;
        List<Integer> list=new ArrayList<>();
        int x1=x;
        while(x>0){
            list.add(x%10);
            x=x/10;
        }
        int r=0;
        for(int i=0;i<list.size();i++){
            r+=list.get(i)*Math.pow(10,list.size()-i-1);
        }
        return r==x1;
    }

    public static void main(String[] args) {
        int num=Integer.MIN_VALUE+1;
        System.out.println(Integer.MIN_VALUE);
        System.out.println( Math.pow(2,31)-1);
        int[][] intervals  ={{1,3},{2,6},{8,10},{15,18}};
        System.out.println( intervals[1][1]);
        System.out.println(isPalindrome(2147483647));
    }
}
