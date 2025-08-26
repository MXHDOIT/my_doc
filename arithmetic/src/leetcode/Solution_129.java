package leetcode;

import leetcode.model.TreeNode;

/**
 * @author: maxinhang.
 */
public class Solution_129 {
    int ans = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int parentNum) {
        int currentNum = parentNum * 10 + root.val;
        if (root.right == null && root.left == null) {
            ans += currentNum;
            return;
        }
        if (root.left != null) {
            dfs(root.left, currentNum);
        }
        if (root.right != null) {
            dfs(root.right, currentNum);
        }
    }
}
