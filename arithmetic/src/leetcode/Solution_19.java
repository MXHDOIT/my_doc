package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode emptyHead = new ListNode(-1);
        emptyHead.next = head;
        ListNode fast = emptyHead;
        ListNode slow = emptyHead;
        while (n-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return emptyHead.next;
    }

}
