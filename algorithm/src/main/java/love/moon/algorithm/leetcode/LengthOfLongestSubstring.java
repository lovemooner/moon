package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : ndong
 * date : 2020/12/5 15:52
 * desc :
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        Assert.assertEqual(lengthOfLongestSubstring("a"),1);
        Assert.assertEqual(lengthOfLongestSubstring("love"),4);
        Assert.assertEqual(lengthOfLongestSubstring("lovee"),4);
        Assert.assertEqual(lengthOfLongestSubstring(""),0);
    }

    public static int lengthOfLongestSubstring(String s) {
        int max=0;
        for(int i=0;i<s.length();i++){
            Set<Character> set=new HashSet<>();
            set.add(s.charAt(i));
            for(int j=i+1;j<s.length();j++){
                if(set.contains(s.charAt(j))){
                    break;
                }
                set.add(s.charAt(j));
            }
            max=Math.max(set.size(),max);
        }
        return max;
    }
}
