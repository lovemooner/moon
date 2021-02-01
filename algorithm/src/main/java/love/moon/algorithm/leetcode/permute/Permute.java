package love.moon.algorithm.leetcode.permute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permute {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permute sol = new Permute();
        List<List<Integer>> lists = sol.permute(nums);
        System.out.println(lists);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        backtrack(nums.length, output,  0,res);
        return res;
    }

    public void backtrack(int n, List<Integer> output,  int first,List<List<Integer>> res) {
        if (first == n) { // 所有数都填完了
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);// 动态维护数组
            // 继续递归填下一个数
            backtrack(n, output,  first + 1,res);
            Collections.swap(output, first, i); // 撤销操作
        }
    }


}
