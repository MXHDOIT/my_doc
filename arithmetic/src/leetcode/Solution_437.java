package leetcode;

import leetcode.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: maxinhang.
 * @version: 2025/8/10 13:08.
 */
public class Solution_437 {
    int ans = 0;

    public int pathSum(TreeNode root, int targetSum) {
        // 初始化哈希表，放入前缀和为0的情况
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0L, 1);
        pathSum(root, targetSum,cnt, 0);
        return ans;
    }

    public void pathSum(TreeNode root, long targetSum, Map<Long, Integer> cnt, long sum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        long diffSum = sum - targetSum;
        ans += cnt.getOrDefault(diffSum, 0);
        cnt.merge(sum, 1, Integer::sum);
        pathSum(root.left, targetSum, cnt, sum);
        pathSum(root.right, targetSum, cnt, sum);
        cnt.merge(sum, -1, Integer::sum);
    }
}
