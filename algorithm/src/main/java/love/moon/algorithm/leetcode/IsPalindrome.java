package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

/**
 * @author : ndong
 * date : 2020/12/5 20:34
 * desc :
 */
public class IsPalindrome {

    public static void main(String[] args) {
        Assert.assertTrue(isPalindrome(121));
    }

    public static boolean isPalindrome(int x) {
        int n=0;
        while(x>0){
            n=10*n+x%10;
            x=x/10;
        }
        return n==x;
    }
}
