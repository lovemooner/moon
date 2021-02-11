package love.moon.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * nums包含重复元素
 * @author : ndong
 * date : 2021/2/11 15:09
 * desc :
 */
public class Permute47 {


    public static void main(String[] args) {
        int[] nums1 = {1, 1, 3};
        int[] nums2 = {1, 2, 2};
        Permute47 sol = new Permute47();
        List<List<Integer>> lists = sol.permute(nums2);
        System.out.println(lists);
    }

    public List<List<Integer>> permute(int[] nums) {
        int len=nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[len];
        List<Integer> output = new ArrayList<>(len);
        dfs(nums, used, output, 0, result);
        return result;
    }

    private void dfs(int[] nums, boolean[] used, List<Integer> output, int index
            , List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(output));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i>0&& used[i-1]&&nums[i] == nums[i - 1])) {
                continue;
            }
            output.add(nums[i]);
            used[i] = true;
            dfs(nums, used, output, index + 1, result);
            used[i] = false;
            output.remove(index);
        }
    }
}
