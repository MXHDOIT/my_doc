package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode emptyHead = new ListNode(-1);
        ListNode tail = emptyHead;

        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            boolean isDuplicate = false;
            while (next != null && next.val == current.val) {
                next = next.next;
                isDuplicate = true;
            }
            if (!isDuplicate) {
                tail.next = current;
                tail = tail.next;
            }
            current = next;
        }
        tail.next = null;
        return emptyHead.next;
    }

}
