package leetcode;

import leetcode.model.TreeNode;

/**
 * @author: maxinhang.
 * @version: 2025/8/9 16:30.
 */
public class Solution_108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }
        int mid = (startIndex + endIndex) / 2;
        int num = nums[mid];
        TreeNode treeNode = new TreeNode(num);
        treeNode.left = sortedArrayToBST(nums, startIndex, mid - 1);
        treeNode.right = sortedArrayToBST(nums, mid + 1, endIndex);
        return treeNode;
    }
}
