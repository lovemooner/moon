package love.moon.algorithm.leetcode;

/**
 * @author : ndong
 * date : 2020/12/6 20:27
 * desc :
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(5>>5);


        String[] strs = {"flower", "flow", "flight"};
//        String[] strs = {"ab", "a"};
        String result = longestCommonPrefix(strs);
        System.out.println(result);
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        String s = strs[0];
        int i=0;
        for (; i < s.length(); i++) {
            String sub = s.substring(i, i+1);
            for (int j = 0; j < strs.length; j++) {
                String str = strs[j];
                if (i>=str.length()||!sub.equals(str.substring(i, i+1))) {
                    return s.substring(0,i);
                }
            }
        }
        return s.substring(0,i);
    }


}
