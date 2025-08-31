package leetcode;

import leetcode.model.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author: maxinhang.
 * @version: 2025/8/31 11:17.
 */
public class Solution_662 {

    public int widthOfBinaryTreeOpt(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Map<TreeNode, Integer> node2Index = new HashMap<>();
        node2Index.put(root, 1);
        int ans = 0;
        int start = 0;
        int end = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                Integer index = node2Index.get(poll);
                if (i == 0) start = index;
                if (i == size - 1) end = index;

                if (poll.left != null) {
                    queue.offer(poll.left);
                    node2Index.put(poll.left, index * 2 );
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    node2Index.put(poll.right, index * 2 + 1);
                }
            }
            ans = Math.max(ans, end - start + 1);
        }

        return ans;
    }

    public int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    sb.append(" ");
                    queue.offer(null);
                    queue.offer(null);
                } else {
                    sb.append("1");
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            int length = sb.toString().trim().length();
            ans = Math.max(ans, length);
            if (length == 0) {
                break;
            }
        }
        return ans;
    }
}
