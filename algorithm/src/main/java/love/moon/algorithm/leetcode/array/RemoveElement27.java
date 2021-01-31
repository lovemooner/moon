package love.moon.algorithm.leetcode.array;

/**
 * @author : ndong
 * date : 2021/1/31 13:52
 * desc :
 */
public class RemoveElement27 {

    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j;
    }

    public static void main(String[] args) {
        RemoveElement27 sol=new RemoveElement27();
        int[] nums1 = {2, 3,7, 1, 1, 4};
        sol.removeElement(nums1,1);
    }

}
