package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: maxinhang.
 */
public class Trie {

    private Node root;

    public Trie() {
        root = new Node();
    }

    void insert(String word) {
        char[] chars = word.toCharArray();
        Node currentNode = root;
        for (char ch : chars) {
            Map<Character, Node> children = currentNode.children;
            if (children.containsKey(ch)) {
                currentNode = children.get(ch);
            } else {
                Node node = new Node(ch);
                children.put(ch, node);
                currentNode = node;
            }
        }
        if (!currentNode.children.containsKey(null)) {
            currentNode.children.put(null,new Node());
        }
    }

    boolean search(String word) {
        char[] chars = word.toCharArray();
        Node currentNode = root;
        for (char ch : chars) {
            Map<Character, Node> children = currentNode.children;
            if (children.containsKey(ch)) {
                currentNode = children.get(ch);
            } else {
                return false;
            }
        }
        return currentNode.children.containsKey(null);
    }

    boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        Node currentNode = root;
        for (char ch : chars) {
            Map<Character, Node> children = currentNode.children;
            if (children.containsKey(ch)) {
                currentNode = children.get(ch);
            } else {
                return false;
            }
        }
        return true;
    }

    static class Node {
        private char ch;
        private Map<Character, Node> children = new HashMap<>();

        public Node() {
        }

        public Node(char ch) {
            this.ch = ch;
        }
    }
}
