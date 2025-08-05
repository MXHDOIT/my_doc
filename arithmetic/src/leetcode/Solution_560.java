package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_560 {

    public int subarraySum1(int[] nums, int k) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    result++;
                }
            }
        }
        return result;
    }
}
