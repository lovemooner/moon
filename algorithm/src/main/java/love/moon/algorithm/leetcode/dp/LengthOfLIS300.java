package love.moon.algorithm.leetcode.dp;

import love.moon.util.Assert;

/**
 * @author : ndong
 * date : 2021/2/15 22:52
 * desc :
 */
public class LengthOfLIS300 {

    public static void main(String[] args) {
        int[] nums={0,1,0,3,2,3};
        LengthOfLIS300 sol=new LengthOfLIS300();
        Assert.assertEqual(sol.lengthOfLIS(nums),4);
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1,dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
