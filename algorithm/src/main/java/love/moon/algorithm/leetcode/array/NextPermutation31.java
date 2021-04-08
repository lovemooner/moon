package love.moon.algorithm.leetcode.array;

/**
 * @author : ndong
 * date : 2021/2/14 22:40
 * desc :
 */
public class NextPermutation31 {

    public static void main(String[] args) {
        int [] nums={1,5,8,4,7,6,5,3,1};
        NextPermutation31 sol=new NextPermutation31();
        sol.nextPermutation(nums);
        for(int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
