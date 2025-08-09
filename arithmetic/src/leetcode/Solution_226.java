package leetcode;

import leetcode.model.TreeNode;

/**
 * @author: maxinhang.
 * @version: 2025/8/9 11:39.
 */
public class Solution_226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }
}
