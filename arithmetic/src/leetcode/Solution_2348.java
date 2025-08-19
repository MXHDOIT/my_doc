package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_2348 {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        //记录上一个不为0的索引
        int last = -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                ans += i - last;
            } else {
                last = i;
            }
        }
        return ans;
    }
}
