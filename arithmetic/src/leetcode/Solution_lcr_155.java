package leetcode;


import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: maxinhang.
 * @version: 2025/9/21 21:59.
 */
public class Solution_lcr_155 {
    public Node treeToDoublyList(Node root) {
        Node virtualRoot = new Node(-1);
        Node tail = virtualRoot;
        Deque<Node> deque = new LinkedList<>();
        Node currentRoot = root;
        while (!deque.isEmpty() || currentRoot != null) {
            while (currentRoot != null) {
                deque.addLast(currentRoot);
                currentRoot = currentRoot.left;
            }
            if (!deque.isEmpty()) {
                Node poll = deque.pollLast();
                tail.right = poll;
                poll.left = tail;
                tail = poll;
                currentRoot = currentRoot.right;
            }
        }
        Node newHead = virtualRoot.right;
        tail.right = newHead;
        newHead.left = tail;

        return newHead;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
