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
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        Arrays.sort(nums);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, 0, len, target, path, result);
        return result;
    }

    private void dfs(int[] nums, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            if (target - nums[i] < 0) {
                break;
            }
            path.addLast(nums[i]);
            dfs(nums, i, len, target - nums[i], path, result);
            path.removeLast();
        }
    }



}
