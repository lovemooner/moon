package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

/**
 * @author : ndong
 * date : 2020/11/20 15:10
 * desc :
 */
public class CanJump55 {

    public static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                int j = i - 1;
                for (; j >= 0; j--) {
                    if (nums[j] > i - j || (i == nums.length - 1 && nums[j] >= i - j)) {
                        break;
                    }
                }
                if (j < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean canJump1(int[] nums) {
        if (nums.length == 1) return true;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                i--;
                if (nums[i] > nums.length - i || (i == nums.length - 1 && nums[i] >= nums.length - i)) {
                    break;
                }
                if (i < 0) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[] nums = {0};
        Assert.assertTrue(canJump(nums));
        int[] nums1 = {2, 3, 1, 1, 4};
        Assert.assertTrue(canJump(nums1));
        int[] nums2 = {3, 2, 1, 0, 4};
        Assert.assertFalse(canJump(nums2));
        int[] nums3 = {0, 1};
        Assert.assertFalse(canJump(nums3));
        int[] nums4 = {2, 0};
        Assert.assertTrue(canJump(nums4));
        int[] nums5 = {2, 0, 0};
        Assert.assertTrue(canJump(nums5));
    }

}
