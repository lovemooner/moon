package love.moon.algorithm.leetcode.array;

/**
 * @author : ndong
 * date : 2021/2/10 20:42
 * desc :
 */
public class LargestNumber179 {

    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE+1);

        LargestNumber179 sol = new LargestNumber179();
        int[] nums1 = {3, 30, 34, 5, 9};
        int[] nums2 = {34323, 3432};
        int[] nums3 = {10, 2, 9, 39, 17};
        int[] nums4 = {999999991, 9};
        System.out.println(sol.largestNumber(nums4));
    }


    public String largestNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length - i; j++) {
                if (compare(nums[j], nums[j + 1]) > 0) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) break;
        }
        if (nums[nums.length-1]==0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = nums.length - 1; i >= 0; i--) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }

    private int compare(int num1, int num2) {
        String result1 = String.valueOf(num1) + num2;
        String result2 = String.valueOf(num2) + num1;
        return result1.compareTo(result2);
    }

}
