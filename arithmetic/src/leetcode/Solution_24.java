package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_24 {
    public ListNode swapPairs(ListNode head) {
        ListNode virtualHead = new ListNode(-1);
        virtualHead.next = head;
        ListNode pre = virtualHead;
        ListNode cur = null;
        ListNode next = null;
        while (pre.next != null && pre.next.next != null) {
            cur = pre.next;
            next = cur.next;
            pre.next = next;
            cur.next = next.next;
            next.next = cur;
            pre = cur;
        }
        return virtualHead.next;
    }
}
