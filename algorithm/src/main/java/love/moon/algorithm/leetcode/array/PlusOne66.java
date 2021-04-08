package love.moon.algorithm.leetcode.array;

public class PlusOne66 {

    public static void main(String[] args) {
        int [] nums={4,3,2,9};
        PlusOne66 sol=new PlusOne66();
        sol.plusOne(nums);
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


}
