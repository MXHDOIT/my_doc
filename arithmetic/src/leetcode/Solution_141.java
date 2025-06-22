package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 * @version: 2025/6/22 20:18.
 */
public class Solution_141 {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
