package leetcode;

import leetcode.model.TreeNode;

/**
 * @author: maxinhang.
 * @version: 2025/8/9 11:35.
 */
public class Solution_104 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
