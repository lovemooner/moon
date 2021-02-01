package love.moon.algorithm.leetcode.linked;

import love.moon.algorithm.leetcode.list.ListNode;

public class ReverseList206 {


    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList1(ListNode head) {
       ListNode curr=head,pre=null;
       while (curr!=null){
           ListNode next= curr.next;
           curr.next=pre;
           pre=curr;
           curr= next;
       }
       return pre;
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode curr=head;
        curr.next = new ListNode(2);
        curr = curr.next;
        curr.next = new ListNode(3);
        curr = curr.next;
        curr.next = new ListNode(4);
        curr = curr.next;
        curr.next = new ListNode(5);
        ReverseList206 sol=new ReverseList206();

        ListNode newNode= sol.reverseList1(head);

        while (newNode!=null){
            System.out.println(newNode.val);
            newNode=newNode.next;
        }

    }
}
