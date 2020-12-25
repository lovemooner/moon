package love.moon.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : ndong
 * date : 2020/12/20 21:19
 * desc :
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
    }

    public static String removeDuplicateLetters(String s) {
        Set<Character> set=new HashSet<>();
        StringBuilder sb=new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){
            if(!set.contains(s.charAt(i))){
                sb.append(s.charAt(i));
                set.add(s.charAt(i));
            }
        }
        StringBuilder sb1=new StringBuilder();
        String s1= sb.toString();
        for(int i=s1.length()-1;i>=0;i++){
            sb1.append(s1.charAt(i));
        }
        return sb1.toString();

    }
}
