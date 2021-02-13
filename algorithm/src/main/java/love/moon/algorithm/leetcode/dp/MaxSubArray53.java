package love.moon.algorithm.leetcode.dp;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @author : ndong
 * date : 2021/2/12 17:15
 * desc :
 */
public class MaxSubArray53 {

    public int maxSubArray(int[] nums) {
        int pre=0,max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }

}
