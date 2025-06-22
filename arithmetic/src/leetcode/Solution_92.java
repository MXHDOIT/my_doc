package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 * @version: 2025/6/22 19:53.
 */
public class Solution_92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode vNode = new ListNode(-1);
        vNode.next = head;
        ListNode prev = vNode;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode cNode = prev.next;
        ListNode nNode = null;
        for (int i = left; i < right; i++) {
            nNode = cNode.next;
            cNode.next = nNode.next;
            nNode.next = prev.next;
            prev.next = nNode;
        }
        return vNode.next;
    }
}
