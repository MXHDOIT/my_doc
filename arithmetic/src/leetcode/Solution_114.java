package leetcode;

import leetcode.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: maxinhang.
 * @version: 2025/8/10 11:21.
 */
public class Solution_114 {
    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode newRoot = new TreeNode(-1);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode right = node.right;
            if (right != null) {
                stack.push(right);
                node.right = null;
            }
            TreeNode left = node.left;
            if (left != null) {
                stack.push(left);
                node.left = null;
            }
            newRoot.right = node;
            newRoot = newRoot.right;
        }
        return;
    }
}
