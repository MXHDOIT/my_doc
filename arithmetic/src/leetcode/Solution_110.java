package leetcode;

import leetcode.model.TreeNode;

/**
 * @author: maxinhang.
 */
public class Solution_110 {
    boolean ans = true;

    public boolean isBalancedOpt(TreeNode root) {
        heightOpt(root);
        return ans;
    }

    private int heightOpt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        ans = ans & Math.abs(left - right) <= 1;

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        //当前节点左右子树高度不超过1 & 左右子树平衡
        return Math.abs(height(root.right) - height(root.left)) <= 1 &&
                isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
