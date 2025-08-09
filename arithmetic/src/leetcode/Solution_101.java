package leetcode;

import leetcode.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: maxinhang.
 * @version: 2025/8/9 11:44.
 */
public class Solution_101 {

    public boolean isSymmetric1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode u = q.poll();
            TreeNode v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if (u == null || v == null || u.val != v.val) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
