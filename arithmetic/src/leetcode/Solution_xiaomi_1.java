package leetcode;

import leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 搜查二叉树构建小顶堆
 *
 * @author: maxinhang.
 */
public class Solution_xiaomi_1 {
    public TreeNode buildHeapBySearchTree(TreeNode treeNode) {
        //中序遍历 -> 有序链表
        List<TreeNode> treeNodes = new ArrayList<>();
        TreeNode currentNode = treeNode;
        Stack<TreeNode> stack = new Stack<>();
        while (currentNode != null || !stack.empty()) {
            if (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            } else {
                TreeNode node = stack.pop();
                treeNodes.add(node);
                currentNode = node.right;
            }
        }
        //有序链表 -> 小顶堆
        int size = treeNodes.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = treeNodes.get(i);
            int leftIndex = i * 2 + 1;
            if (leftIndex < size) {
                node.left = treeNodes.get(leftIndex);
            } else {
                node.left = null;
            }
            int rightIndex = i * 2 + 2;
            if (rightIndex < size) {
                node.right = treeNodes.get(rightIndex);
            } else {
                node.right = null;
            }
        }
        return treeNodes.get(0);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        TreeNode treeNode = new Solution_xiaomi_1().buildHeapBySearchTree(root);
        System.out.println(treeNode);
    }
}
