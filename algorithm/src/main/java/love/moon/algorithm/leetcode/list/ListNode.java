package love.moon.algorithm.leetcode.list;

/**
 * @author : ndong
 * date : 2020/11/15 17:59
 * desc :
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
