package leetcode;

import leetcode.model.ListNode;

/**
 * 反转链表.
 * @author: maxinhang.
 */
public class Solution_206 {
    public ListNode reverseList(ListNode head) {
        ListNode currentNode = head;
        ListNode pre = null;
        ListNode next;
        while (currentNode != null) {
            next = currentNode.next;

            currentNode.next = pre;
            pre = currentNode;
            currentNode = next;
        }
        return pre;
    }
}
