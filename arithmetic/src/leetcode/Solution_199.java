package leetcode;

import leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: maxinhang.
 */
public class Solution_199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Queue<TreeNode> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    temp.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    temp.offer(treeNode.right);
                }
                if (queue.isEmpty()) {
                    result.add(treeNode.val);
                }
            }
            queue = temp;
        }
        return result;
    }
}
