package leetcode;

import leetcode.model.TreeNode;

/**
 * @author: maxinhang.
 * @version: 2025/8/9 12:25.
 */
public class Solution_543 {
    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return ans;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        ans = Math.max(ans, (left + right));
        return Math.max(left, right) + 1;
    }
}
