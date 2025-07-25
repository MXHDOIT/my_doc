package leetcode;

import leetcode.model.ListNode;

/**
 * @author: maxinhang.
 */
public class Solution_142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (slowNode == fastNode) {
                //相遇之后将整体分为3段 a:环外部分 b:环内slow走过部分 c:环内slow剩余部分
                // a+b+n(b+c) = 2(a+b)  => a = (n-1)(b+c) + c
                ListNode cur = head;
                while (cur != slowNode) {
                    cur = cur.next;
                    slowNode = slowNode.next;
                }
                return cur;
            }
        }
        return null;
    }
}
