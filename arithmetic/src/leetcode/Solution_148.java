package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_148 {
    /**
     * 解题思路：
     * 分治 归并排序
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        //1. 找到中心节点，分治进行排序
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = null;
        ListNode list1 = sortList(head);
        ListNode list2 = sortList(slow);

        ListNode newHead = new ListNode(-1);
        ListNode current = newHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }
        return newHead.next;
    }
}
