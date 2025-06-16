package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode node = new ListNode(-1);
        ListNode tail = node;
        ListNode current = head;
        int size = 0;
        while (current != null) {
            size++;
            ListNode temp = current.next;
            if (size == k) {
                current.next = null;
                tail.next = reverse(head);
                tail = head;
                head = temp;
                size = 0;
            }
            current = temp;
        }
        tail.next = head;
        return node.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;

        while (current != null) {
            next = current.next;

            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
