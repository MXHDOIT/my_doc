package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_61 {
    public ListNode rotateRight(ListNode head, int k) {
        // 处理空链表或只有一个节点的情况
        if (head == null || head.next == null) return head;

        // 计算链表长度并且找到尾节点
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 计算有效旋转步数
        k = k % length;
        if (k == 0) {
            return head;
        }

        // 找到新的头节点的前一个节点
        ListNode newTail = head;
        for (int i = 1; i < length - k; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
    }
}
