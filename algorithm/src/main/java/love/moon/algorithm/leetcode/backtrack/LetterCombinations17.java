package love.moon.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 *
 * @author : ndong
 * date : 2021/2/11 23:14
 * desc :
 */
public class LetterCombinations17 {

    public static void main(String[] args) {
        LetterCombinations17 sol = new LetterCombinations17();
        String digits = "23";
        List<String> result = sol.letterCombinations(digits);
        System.out.println(result);
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits==null||digits.length()==0) return result;
        StringBuffer  output = new StringBuffer();
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");put('3', "def");put('4', "ghi");
            put('5', "jkl");put('6', "mno");put('7', "pqrs");
            put('8', "tuv");put('9', "wxyz");
        }};
        dfs(phoneMap, digits, output, 0, result);
        return result;
    }


    private void dfs(Map<Character, String> phoneMap, String digits, StringBuffer output, int index, List<String> result) {
        if (digits.length() == index) {
            result.add(output.toString());
            return;
        }
        String str = phoneMap.get(digits.charAt(index));
        for (int i = 0; i < str.length(); i++) {
            output.append(str.charAt(i));
            dfs(phoneMap, digits, output, index + 1, result);
            output.deleteCharAt(output.length() - 1);
        }
    }
}
