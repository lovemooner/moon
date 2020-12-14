package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : ndong
 * date : 2020/12/5 21:11
 * desc :
 */
public class RomanToInt {

    public static void main(String[] args) {
//        Assert.assertEqual(romanToInt("IVIVV"), 13);
//        Assert.assertEqual(romanToInt("MCMXCIV"), 1994);
        Assert.assertEqual(romanToInt("III"), 3);
    }

    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        int i = 0;
        int result = 0;
        while (i < s.length()) {
            if (i + 1 < s.length() &&map.get(s.substring(i, i + 2)) != null) {
                result += map.get(s.substring(i, i + 2));
                i += 2;
            }else {
                result += map.get(s.substring(i,++i));
            }
        }
        return result;
    }
}
