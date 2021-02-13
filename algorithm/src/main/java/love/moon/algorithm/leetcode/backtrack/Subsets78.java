package love.moon.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * @author : ndong
 * date : 2021/2/12 13:22
 * desc :
 */
public class Subsets78 {

    public static void main(String[] args) {
        Subsets78 sol=new Subsets78();
        int[] nums={1,2,3};
        List<List<Integer>> result = sol.subsets(nums);
        System.out.println(result);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if(nums==null||nums.length==0) return result;
        List<Integer> output = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums,0,used,0,output,result);
        return result;
    }

    private void dfs(int[] nums, int begin,boolean[] used, int index, List<Integer> output, List<List<Integer>> result) {
        if (index == nums.length) return;
        for (int i = begin; i < nums.length; i++) {
            if(used[i]) continue;
            output.add(nums[i]);
            result.add(new ArrayList<>(output));
            used[i]=true;
            dfs(nums,i+1,used,index+1,output,result);
            used[i]=false;
            output.remove(output.size()-1);
        }
    }


}
