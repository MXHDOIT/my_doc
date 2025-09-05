package leetcode;

import leetcode.model.TreeNode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author: maxinhang.
 */
public class Codec {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(-1);
        treeNode.right = new TreeNode(2);
        new Codec().serialize(treeNode);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean islast = true;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (node.left != null || node.right != null) {
                        islast = false;
                    }
                    queue.offer(node.left);
                    queue.offer(node.right);
                    sb.append(node.val);
                } else {
                    queue.offer(null);
                    queue.offer(null);
                    sb.append("null");
                }
                sb.append(",");
            }
            if (islast) {
                break;
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        String[] split = data.split(",");
        int n = split.length;
        TreeNode[] treeNodes = new TreeNode[split.length];
        for (int i = n - 1; i >= 0; i--) {
            if (Objects.equals(split[i], "null")) {
                treeNodes[i] = null;
            } else {
                treeNodes[i] = new TreeNode(Integer.parseInt(split[i]));
                int left = i * 2 + 1;
                if (left < n) {
                    treeNodes[i].left = treeNodes[left];
                    int right = left + 1;
                    treeNodes[i].right = treeNodes[right];
                }
            }
        }
        return treeNodes[0];
    }
}
