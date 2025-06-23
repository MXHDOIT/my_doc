package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKLists(lists,0,lists.length-1);
    }

    public ListNode mergeKLists(ListNode[] lists, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return lists[startIndex];
        }
        int midIndex = (startIndex + endIndex) / 2;
        ListNode leftListNode = mergeKLists(lists, startIndex, midIndex);
        ListNode rightListNode = mergeKLists(lists, midIndex + 1, endIndex);
        ListNode node = new ListNode(-1);
        ListNode currentNode = node;
        while (leftListNode != null && rightListNode != null) {
            if (leftListNode.val <= rightListNode.val) {
                currentNode.next = leftListNode;
                leftListNode = leftListNode.next;
            } else {
                currentNode.next = rightListNode;
                rightListNode = rightListNode.next;
            }
            currentNode = currentNode.next;
        }
        if (leftListNode != null) {
            currentNode.next = leftListNode;
        }
        if (rightListNode != null) {
            currentNode.next = rightListNode;
        }
        return node.next;
    }
}
