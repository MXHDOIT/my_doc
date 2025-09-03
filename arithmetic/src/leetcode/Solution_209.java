package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_209 {

    public static void main(String[] args) {
        new Solution_209().minSubArrayLen(4,new int[]{1,4,4});
    }
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < n) {
            sum += nums[right];
            while (sum >= target) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
