package love.moon.algorithm.leetcode.priorityqueue;

import love.moon.util.Assert;

import java.util.PriorityQueue;

/**
 * @author : ndong
 * date : 2021/2/11 15:42
 * desc :
 */
public class KthLargest703 {
    private PriorityQueue<Integer> pq;
    private int k;

    public KthLargest703(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums={4, 5, 8, 2};
        KthLargest703 sol=new KthLargest703(3, nums);
        Assert.assertEqual(sol.add(3),4);
        Assert.assertEqual(sol.add(5),5);

    }
}

