package leetcode;

import leetcode.model.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: maxinhang.
 * @version: 2025/8/10 13:27.
 */
public class Solution_105 {

//    preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]

    public static void main(String[] args) {
        new Solution_105().buildTree(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> num2Index = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            num2Index.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, 0, num2Index);
    }

    private TreeNode buildTree(int[] preorder, int pStartIndex, int pEndIndex,
                               int iStartIndex, Map<Integer, Integer> inorderNum2Index) {
        if (pStartIndex > pEndIndex) {
            return null;
        }
        int rootValue = preorder[pStartIndex];
        TreeNode treeNode = new TreeNode(rootValue);
        if (pStartIndex == pEndIndex) {
            return treeNode;
        }
        Integer index = inorderNum2Index.get(rootValue);
        int leftSize = index - iStartIndex;
        treeNode.left =
                buildTree(preorder, pStartIndex + 1, pStartIndex + leftSize, iStartIndex, inorderNum2Index);
        treeNode.right =
                buildTree(preorder, pStartIndex + leftSize + 1, pEndIndex, index + 1, inorderNum2Index);
        return treeNode;
    }

}
