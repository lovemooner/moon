package love.moon.algorithm.leetcode.array;

import java.util.*;

public class FindKthLargest215 {

    public static void main(String[] args) {
//        int [] nums={3,2,3,1,2,4,5,5,6};
        int [] nums={1,2,2,3,3,4,5,5,6};
        System.out.println(findKthLargest(nums,3));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> n1 - n2);
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }
}
