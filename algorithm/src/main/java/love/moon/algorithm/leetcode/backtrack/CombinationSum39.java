package love.moon.algorithm.leetcode.backtrack;

import java.util.*;

/**
 * @author : ndong
 * date : 2021/2/9 21:21
 * desc :
 */
public class CombinationSum39 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 6,7};
        CombinationSum39 sol = new CombinationSum39();
        List<List<Integer>> lists = sol.combinationSum(nums,7);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<Integer> output = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, target, output, result);
        return result;
    }

    private void dfs(int[] nums, int begin,  int target, List<Integer> output, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(output));
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            if (nums[i]>target) break;
            output.add(nums[i]);
            dfs(nums, i, target - nums[i], output, result);
            output.remove(output.size()-1);
        }
    }


}
