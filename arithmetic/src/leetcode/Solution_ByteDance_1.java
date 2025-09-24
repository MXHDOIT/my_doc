package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_ByteDance_1 {

    public static void main(String[] args) {
        Solution_ByteDance_1 solutionByteDance1 = new Solution_ByteDance_1();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(8);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(6);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(4);
        listNode.next.next.next.next.next.next = new ListNode(7);
        listNode.next.next.next.next.next.next.next = new ListNode(2);
        ListNode sort = solutionByteDance1.sort(listNode);
        System.out.println(sort);
    }

    //https://mp.weixin.qq.com/s/0WVa2wIAeG0nYnVndZiEXQ
    public ListNode sort(ListNode listNode) {
        if (listNode == null || listNode.next == null) return listNode;
        //Step1 拆分成 升序链表、降序链表
        ListNode virtualHead1 = new ListNode(-1);
        ListNode tail1 = virtualHead1;
        ListNode virtualHead2 = new ListNode(-1);
        ListNode tail2 = virtualHead2;
        int index = 1;
        while (listNode != null) {
            if (index % 2 == 1) {
                tail1.next = listNode;
                tail1 = listNode;
            } else {
                tail2.next = listNode;
                tail2 = listNode;
            }
            index++;
            listNode = listNode.next;
        }
        tail1.next = null;
        tail2.next = null;
        //Step2 翻转降序链表
        ListNode reverse = reverse(virtualHead2.next);
        //Step3 合并两个升序链表
        return mergeSortListNode(virtualHead1.next, reverse);
    }

    private ListNode mergeSortListNode(ListNode listNode1, ListNode listNode2) {
        ListNode virtualHead = new ListNode(-1);
        ListNode tail = virtualHead;
        while (listNode1 != null && listNode2 != null) {
            if (listNode1.val <= listNode2.val) {
                tail.next = listNode1;
                listNode1 = listNode1.next;
            } else {
                tail.next = listNode2;
                listNode2 = listNode2.next;
            }
            tail = tail.next;
        }
        if (listNode1 != null) {
            tail.next = listNode1;
        }
        if (listNode2 != null) {
            tail.next = listNode2;
        }
        return virtualHead.next;
    }

    private ListNode reverse(ListNode listNode) {
        ListNode curNode = listNode;
        ListNode pre = null;
        ListNode next = null;
        while (curNode != null) {
            next = curNode.next;
            curNode.next = pre;
            pre = curNode;
            curNode = next;
        }
        return pre;
    }
}
