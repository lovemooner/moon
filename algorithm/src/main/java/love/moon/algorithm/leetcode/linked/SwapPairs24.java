package love.moon.algorithm.leetcode.linked;

public class SwapPairs24 {

    public ListNode swapPairs(ListNode head) {
        ListNode curr = head,pre=null;
        head = head.next;
        while (curr != null) {
            ListNode next = curr.next;
            if (next == null) {
                break;
            }
            curr.next = next.next;
            next.next = curr;
            if(pre!=null)
            pre.next=next;
            pre=curr;
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode curr = head;
        curr.next = new ListNode(2);
        curr = curr.next;
        curr.next = new ListNode(3);
        curr = curr.next;
        curr.next = new ListNode(4);
        curr = curr.next;
        curr.next = new ListNode(5);
        SwapPairs24 sol = new SwapPairs24();

        ListNode newNode = sol.swapPairs(head);
        while (newNode != null) {
            System.out.println(newNode.val);
            newNode = newNode.next;
        }
    }

}
