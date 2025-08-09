package leetcode.model;

/**
 * @author: maxinhang.
 * @version: 2025/8/9 11:25.
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
