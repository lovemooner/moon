package love.moon.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges228 {

    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums.length < 1) return list;
        String tmp = null;
        for (int i = 1; i < nums.length; i++) {
            if (tmp == null) {
                tmp = String.valueOf(nums[0]);
                continue;
            }
            if (nums[i] - nums[i - 1] == 1) {
                continue;
            } else {
                list.add(tmp + "-->" + nums[i]);
                tmp = null;
            }
        }
        if (tmp != null)
            list.add(tmp);
        return list;
    }

    public static List<String> summaryRanges1(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums.length < 1) return list;
        int i = 1, j = 0;
        for (; i < nums.length; i++) {
            if (i+1 < nums.length&&nums[i] - nums[i + 1] != -1) {
                if(i-j==1){
                    list.add(nums[j]+"");
                }else {
                    list.add(nums[j] + "-->" + nums[i]);
                }
                j = ++i;
            }
        }
        if (j + 1 == nums.length) {
            list.add(nums[j - 1] + "");
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7};
        System.out.println(summaryRanges1(nums));
    }

}
