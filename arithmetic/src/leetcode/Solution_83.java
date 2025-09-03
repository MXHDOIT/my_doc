package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode tail = head;
        ListNode currentNode = head.next;
        while (currentNode != null) {
            if (currentNode.val != tail.val) {
                tail.next = currentNode;
                tail = currentNode;
            }
            currentNode = currentNode.next;
        }
        tail.next = null;
        return head;
    }
}
