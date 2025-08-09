package leetcode;

import leetcode.model.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: maxinhang.
 * @version: 2025/8/9 11:22.
 */
public class Solution_138 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();

        Node newHead = new Node(-1);
        Node newTail = newHead;
        Node currentNode = head;

        while (currentNode != null) {
            Node node = new Node(currentNode.val);
            map.put(currentNode, node);
            newTail.next = node;
            newTail = newTail.next;
            currentNode = currentNode.next;
        }

        currentNode = head;
        newTail = newHead.next;

        while (currentNode != null) {
            Node random = currentNode.random;
            if (random != null) {
                Node node = map.get(random);
                newTail.random = node;
            }
            currentNode = currentNode.next;
            newTail = newTail.next;
        }

        return newHead.next;
    }
}
