package leetcode;

import leetcode.model.TreeNode;

import java.util.Stack;

/**
 * @author: maxinhang.
 * @version: 2025/8/9 16:58.
 */
public class Solution_230 {
    public int kthSmallest(TreeNode root, int k) {
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int size = 0;
        while (current != null || !stack.isEmpty()) {
            if (current == null) {
                TreeNode pop = stack.pop();
                size++;
                if (size == k) {
                    return pop.val;
                }
                current = pop.right;
            }else {
                stack.push(current);
                current = current.left;
            }
        }
        return -1;
    }
}
