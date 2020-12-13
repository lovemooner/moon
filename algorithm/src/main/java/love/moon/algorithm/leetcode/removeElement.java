package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

/**
 * @author : ndong
 * date : 2020/12/1 18:58
 * desc :
 */
public class removeElement {



    public static int removeElement(int[] nums, int val) {
        if(nums.length==0) return 0;
        int j=0;
        for(int i=0;;i++,j++){
            while(i<nums.length&&nums[i]==val){
                i++;
            }
            if(i>=nums.length){
                break;
            }
            if(j<i&&i<nums.length){
                nums[j]=nums[i];
            }
        }
        return j;
    }

    public static int removeElement1(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public static void main(String[] args) {
//        Assert.assertEqual(removeElement1(new int []{2},3),1);
//        Assert.assertEqual(removeElement1(new int []{3,2,2,3},3),2);
        Assert.assertEqual(removeElement1(new int []{0,1,2,2,3,0,4,2},2),5);
    }
}
