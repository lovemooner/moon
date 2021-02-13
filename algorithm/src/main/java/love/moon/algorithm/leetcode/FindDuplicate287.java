package love.moon.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author : ndong
 * date : 2021/2/12 20:37
 * desc :
 */
public class FindDuplicate287 {

    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1]){
                return nums[i];
            }
        }
        return -1;
    }
}
