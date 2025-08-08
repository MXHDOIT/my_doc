package leetcode;

import leetcode.model.ListNode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_234 {

    public boolean isPalindrome1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode halfNode = reversal(slow);

        while (head != null && halfNode != null) {
            if (head.val != halfNode.val) {
                return false;
            }
            head = head.next;
            halfNode = halfNode.next;
        }

        return halfNode == null || halfNode.next == null;
    }

    private static ListNode reversal(ListNode slow) {
        ListNode prev = null;
        ListNode currentNode = slow;
        ListNode next;
        while (currentNode != null) {
            next = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = next;
        }
        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        ListNode node = head;
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }

        node = head;
        while (node != null) {
            Integer pop = stack.pop();
            if (pop != node.val){
                return false;
            }
            node = node.next;
        }
        return true;
    }
}
