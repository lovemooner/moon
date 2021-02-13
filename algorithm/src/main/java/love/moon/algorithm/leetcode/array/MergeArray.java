package love.moon.algorithm.leetcode.array;

import java.util.*;

/**
 * @author : ndong
 * date : 2020/12/2 18:29
 * desc :
 */
public class MergeArray {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1=new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            s1.add(nums1[i]);
        }
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<nums2.length;i++){
            if(s1.contains(nums2[i])&&!list.contains(nums2[i])){
                list.add(nums2[i]);
            }
        }
        int[] a= new int[list.size()];
        for(int i=0;i<list.size();i++){
            a[i]=list.get(i);
        }
      return   a;

    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null) return null;
        Arrays.sort(intervals, (o1, o2) -> {return o1[0] - o2[0];});
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (i != intervals.length - 1 && intervals[i][1] >= intervals[i + 1][0]) {
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
            } else {
                list.add(new int[]{intervals[i][0], intervals[i][1]});
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 4}, {0, 2}, {3, 5}};
        int[][] result = merge(arr);
        System.out.println(result);
    }
}
