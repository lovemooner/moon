package love.moon.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 *
 * @author : ndong
 * date : 2021/2/16 0:13
 * desc :
 */
public class FindDisappearedNumbers448 {

    public static void main(String[] args) {
        int[] nums={1,3,3,5,6,7,8};
        FindDisappearedNumbers448 sol=new FindDisappearedNumbers448();
        System.out.println(sol.findDisappearedNumbers(nums));
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                result.add(i + 1);
            }
        }
        return result;
    }

}
