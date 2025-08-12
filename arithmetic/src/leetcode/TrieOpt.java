package leetcode;

/**
 * @author: maxinhang.
 */
public class TrieOpt {

    private final Node root = new Node();

    public TrieOpt() {

    }

    public void insert(String word) {
        Node curNode = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (curNode.son[index] == null) {
                curNode.son[index] = new Node();
            }
            curNode = curNode.son[index];
        }
        curNode.end = true;
    }

    public boolean search(String word) {
        return find(word) == 2;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != 0;
    }

    private int find(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (cur.son[c] == null) { // 道不同，不相为谋
                return 0;
            }
            cur = cur.son[c];
        }
        // 走过同样的路（2=完全匹配，1=前缀匹配）
        return cur.end ? 2 : 1;
    }

    static class Node {
        Node[] son = new Node[26];
        boolean end = false;
    }
}
