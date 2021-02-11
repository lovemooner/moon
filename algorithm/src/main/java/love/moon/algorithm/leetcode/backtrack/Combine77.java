package love.moon.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 *
 * @author : ndong
 * date : 2021/2/10 14:58
 * desc :
 */
public class Combine77 {

    public static void main(String[] args) {
        Combine77 sol=new Combine77();
        List<List<Integer>> result= sol.combine(4,2);
        System.out.println(result);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> output = new ArrayList<>();
        boolean[] used = new boolean[n];
        List<List<Integer>> result = new ArrayList<>();
        dfs(k, n, used, output, 0, 0,result);
        return result;
    }

    private void dfs(int k, int n, boolean[] used, List<Integer> output, int index, int pi,List<List<Integer>> result) {
        if (output.size() == k) {
            result.add(new ArrayList<>(output));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i]||pi!=0&& i<pi) continue;
            output.add(i+1);
            used[i] = true;
            dfs(k, n, used, output, index + 1, i,result);
            used[i] = false;
            output.remove(index);
        }
    }
}
