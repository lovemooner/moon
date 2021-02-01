package love.moon.algorithm.leetcode.array;

public class SortColors75 {

    public void sortColors1(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr++] = temp;
            }
        }

        for (int i = ptr; i < n; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr++] = temp;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int t = 0, j = nums.length - 1;
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == 2 && i == j+1) {
                break;
            }
            if (nums[i] == 0) {
                int tmp = nums[t];
                nums[t++] = nums[i];
                nums[i++] = tmp;
            } else if (nums[i] == 2) {
                int tmp = nums[j];
                nums[j--] = nums[i];
                nums[i] = tmp;
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors75 sol = new SortColors75();
        sol.sortColors2(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
