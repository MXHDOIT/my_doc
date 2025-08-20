package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_lcr_140 {
    public ListNode trainingPlan(ListNode head, int cnt) {
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (cnt > 0) {
            fastNode = fastNode.next;
            cnt--;
        }
        while (fastNode != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        return slowNode;
    }
}
