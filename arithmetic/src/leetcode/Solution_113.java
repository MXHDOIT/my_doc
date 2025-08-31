package leetcode;

import leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 * @version: 2025/8/31 13:58.
 */
public class Solution_113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, targetSum, ans, new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode root, int targetSum, List<List<Integer>> ans, List<Integer> temp) {
        temp.add(root.val);
        //叶子节点
        if (root.right == null && root.left == null) {
            if (root.val == targetSum) {
                List<Integer> list = new ArrayList<>(temp);
                ans.add(list);
            }

        } else {
            if (root.left != null) {
                dfs(root.left, targetSum - root.val, ans, temp);
            }
            if (root.right != null) {
                dfs(root.right, targetSum - root.val, ans, temp);
            }
        }
        temp.remove(temp.size() - 1);
    }
}
