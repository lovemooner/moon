package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : ndong
 * date : 2020/12/6 1:09
 * desc :
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
//        Assert.assertEqual(threeSum(nums1).size(),2);
        int[] nums2 = {0, 0, 0, 0};
//        Assert.assertEqual(threeSum(nums2).size(), 1);
        int[] nums3 = {-1, 0, 1, 2, -1, -4};
//        Assert.assertEqual(threeSum(nums3).size(), 2);
        int[] nums4 = {1, -1, -1, 0};
        Assert.assertEqual(threeSum1(nums4).size(), 1);

    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 需要和上一次枚举的数不相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            int t = nums.length - 1;
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (j < t && nums[j] + nums[t] + nums[i] > 0) {
                    --t;
                }
                if (j == t) {
                    break;
                }
                if (nums[j] + nums[t] + nums[i] == 0) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[t]);
                    result.add(list);
                }
            }

        }
        return result;
    }

    //    public static List<List<Integer>> threeSum(int[] nums) {
//        Arrays.sort(nums);
//        List<List<Integer>> result = new ArrayList<>();
//        for (int i = 0; i < nums.length - 2; i++) {
//            if (nums[i] > 0) {
//                break;
//            }
//            if (i > 0 && nums[i] == nums[i - 1]) {
//                continue;
//            }
//            for (int j = i + 1; j < nums.length - 1; j++) {
//                if (j > i + 1 && nums[j] == nums[j - 1]) {
//                    continue;
//                }
//                int t = j + 1;
//                while (t < nums.length && nums[i] + nums[j] + nums[t] < 0) {
//                    t++;
//                }
//                if(t>nums.length-1) t=nums.length-1;
//                if (nums[i] + nums[j] + nums[t] == 0) {
//                    List<Integer> list = new ArrayList<>(3);
//                    list.add(nums[i]);
//                    list.add(nums[j]);
//                    list.add(nums[t]);
//                    result.add(list);
//                }
//                if(nums[i] + nums[j] + nums[t]>0){
//                    break;
//                }
//            }
//        }
//        return result;
//    }
}
