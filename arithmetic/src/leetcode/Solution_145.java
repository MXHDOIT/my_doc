package leetcode;

import leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_145 {

    /**
     * 非递归
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.empty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }

        while (!stack2.empty()) {
            result.add(stack2.pop().val);
        }
        return result;
    }

    /**
     * 递归
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversal(result, root);
        return result;
    }

    public void postorderTraversal(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        postorderTraversal(result, root.left);
        postorderTraversal(result, root.right);
        result.add(root.val);
    }
}
