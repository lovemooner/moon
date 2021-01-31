package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

/**
 * @author : ndong
 * date : 2020/11/20 15:10
 * desc :
 */
public class CanJump55 {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        CanJump55 sol=new CanJump55();
        int[] nums1 = {1, 0, 0, 1, 4};
        Assert.assertFalse(sol.canJump(nums1));
        int[] nums2 = {2, 3, 1, 1, 4};
        Assert.assertTrue(sol.canJump(nums2));
    }

}
