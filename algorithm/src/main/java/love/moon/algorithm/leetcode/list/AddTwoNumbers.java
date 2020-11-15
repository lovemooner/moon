package love.moon.algorithm.leetcode.list;

/**
 * @author : ndong
 * date : 2020/11/15 17:58
 * desc :
 */
public class AddTwoNumbers {

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int result = l1.val + l2.val;
        ListNode l3 = new ListNode(result % 10);
        ListNode curr = l3;
        int tmp = result / 10;
        while ((l1 = l1.next) != null) {
            if (l2.next != null) {
                l2 = l2.next;
                result = l1.val + l2.val + tmp;
            }else {
                result = l1.val + tmp;
            }
            curr.next = new ListNode(result % 10);
            curr = curr.next;
            tmp = result / 10;
        }
        while (l2!=null&&(l2=l2.next) != null) {
            result =l2.val + tmp;
            curr.next = new ListNode(result % 10);
            curr = curr.next;
            tmp = result / 10;
        }
        if(tmp!=0){
            curr.next = new ListNode(tmp);
        }
        return l3;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=null,tail=null;
        int carry=0;
        while (l1 != null||l2 != null) {
            int val1=l1==null?0:l1.val;
            int val2=l2==null?0:l2.val;
            int sum=val1+val2+carry;
            if(head==null){
                tail= head=new ListNode(sum%10);
            }else {
                tail.next=new ListNode(sum%10);
                tail=tail.next;
            }
            carry=sum/10;
            if(l1 != null){
                l1=l1.next;
            }
            if(l2 != null){
                l2=l2.next;
            }
        }
        if(carry>0){
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode curr = l1;
        curr.next = new ListNode(4);
        curr = curr.next;
        curr.next = new ListNode(9);


        ListNode l2 = new ListNode(5);
        curr = l2;
        curr.next = new ListNode(6);
        curr = curr.next;
        curr.next = new ListNode(4);
        curr = curr.next;
        curr.next = new ListNode(9);

        ListNode l3 = addTwoNumbers(l1, l2);
        System.out.print(l3.val+" ");
        while ((l3=l3.next)!=null){
            System.out.print(l3.val+" ");
        }

    }

//    public static void main(String[] args) {
//        ListNode l1 = new ListNode(9);
//        ListNode curr = l1;
//        curr.next = new ListNode(9);
//        curr = curr.next;
//        curr.next = new ListNode(9);
//        curr = curr.next;
//        curr.next = new ListNode(9);
//        curr = curr.next;
//        curr.next = new ListNode(9);
//        curr = curr.next;
//        curr.next = new ListNode(9);
//        curr = curr.next;
//        curr.next = new ListNode(9);
//        curr = curr.next;
//
//        ListNode l2 = new ListNode(9);
//        curr = l2;
//        curr.next = new ListNode(9);
//        curr = curr.next;
//        curr.next = new ListNode(9);
//        curr = curr.next;
//        curr.next = new ListNode(9);
//
//        ListNode l3 = addTwoNumbers(l1, l2);
//        System.out.print(l3.val+" ");
//        while ((l3=l3.next)!=null){
//            System.out.print(l3.val+" ");
//        }
//
//    }

}
