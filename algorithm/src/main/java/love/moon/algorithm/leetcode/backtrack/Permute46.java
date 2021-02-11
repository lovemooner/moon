package love.moon.algorithm.leetcode.backtrack;

/**
 * 46. 全排列 I
 * nums包不含重复元素
 * @author : ndong
 * date : 2021/2/9 18:53
 * desc :
 */

import java.util.ArrayList;
import java.util.List;


public class Permute46 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permute46 sol = new Permute46();
        List<List<Integer>> lists = sol.permute(nums);
        System.out.println(lists);
    }

    public List<List<Integer>> permute(int[] nums) {
        int len=nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[len];
        List<Integer> output = new ArrayList<>(len);
        dfs(nums, used, output, 0, result);
        return result;
    }

    /**
     * @param nums
     * @param used   nums的位置状态
     * @param output
     * @param index  output的位置
     * @param result
     */
    private void dfs(int[] nums, boolean[] used, List<Integer> output, int index
            , List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(output));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            output.add(nums[i]);
            used[i] = true;
            dfs(nums, used, output, index + 1, result);
            used[i] = false;
            output.remove(index);
        }
    }


}


