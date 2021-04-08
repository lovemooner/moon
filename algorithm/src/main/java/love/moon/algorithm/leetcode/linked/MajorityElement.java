package love.moon.algorithm.leetcode.linked;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public static int titleToNumber(String s) {
        int r=s.charAt(s.length()-1)-'A'+1;
        for(int i=s.length()-2,j=1;i>=0;i--,j++){
            r+=Math.pow(26,j)*(s.charAt(i)-'A'+1);
        }
        return r;
    }

    public static int titleToNumber1(String s) {
        int ans = 0;
        for(int i=0;i<s.length();i++) {
            int num = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + num;
        }
        return ans;
    }

    public static void main(String[] args) {
        int t='1'-'0';
        System.out.println();
        String s="love";
        for(int i=0;i<s.length();i++){
            System.out.println(s.charAt(i));
        }

        System.out.println(titleToNumber1("ABA"));
    }
}
