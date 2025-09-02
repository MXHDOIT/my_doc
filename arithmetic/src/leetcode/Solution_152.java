package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_152 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] minDp = new int[n];
        int[] maxDp = new int[n];
        int max = minDp[0] = maxDp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            minDp[i] = Math.min(nums[i], Math.min(minDp[i - 1] * nums[i], maxDp[i - 1] * nums[i]));
            maxDp[i] = Math.max(nums[i], Math.max(minDp[i - 1] * nums[i], maxDp[i - 1] * nums[i]));
            max = Math.max(max, maxDp[i]);
        }
        return max;
    }
}
