package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode currentNode= head;
        int flag = 0;
        while (l1 != null || l2 != null || flag != 0) {
            int sum = flag;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            currentNode.next = new ListNode(sum % 10);
            currentNode = currentNode.next;
            flag = sum / 10;
        }
        return head.next;
    }
}