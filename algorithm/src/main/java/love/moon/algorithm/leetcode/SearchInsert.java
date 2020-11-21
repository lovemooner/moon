package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

public class SearchInsert {

    /**
     * 最后一定left=right
     * 所以最后执行while使left=right
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid =(left +right)/2;
            if(target<nums[mid]){
                right= mid-1;
            }else if(target>nums[mid]){
                left=mid+1;
            }else{
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums={1,3,5,6};
        Assert.assertEqual(searchInsert(nums,2),1);
        Assert.assertEqual(searchInsert(nums,0),0);
        int[] nums1={2,5};
        Assert.assertEqual(searchInsert(nums1,1),0);
    }


}
