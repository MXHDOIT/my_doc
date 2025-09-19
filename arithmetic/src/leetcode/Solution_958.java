package leetcode;

import leetcode.model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: maxinhang.
 */
public class Solution_958 {
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> treeNodeDeque = new LinkedList<>();
        treeNodeDeque.addLast(root);
        boolean isExistNull = false;
        while (!treeNodeDeque.isEmpty()) {
            TreeNode treeNode = treeNodeDeque.pollFirst();
            if (treeNode == null) {
                isExistNull = true;
            } else {
                if (isExistNull) {
                    return false;
                }
                treeNodeDeque.addLast(treeNode.left);
                treeNodeDeque.addLast(treeNode.right);
            }
        }
        return true;
    }
}
