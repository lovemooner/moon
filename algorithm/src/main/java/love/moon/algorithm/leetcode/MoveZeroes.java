package love.moon.algorithm.leetcode;

/**
 * @author : ndong
 * date : 2020/11/19 13:58
 * desc :
 */
public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public static void swap(int[] nums, int left, int right) {
        if(left==right) return;
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        int[] nums={0,0,2,1,12};
        moveZeroes(nums);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
    }

}
