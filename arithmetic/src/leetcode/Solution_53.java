package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_53 {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int prev = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            prev = prev + num;
            max = Math.max(max, prev);
            prev = Math.max(prev, 0);
        }
        return max;
    }
}
