package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/8/17 19:46.
 */
public class Solution_287 {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int pre = 0;
        int pre1 = slow;
        while (pre1 != pre) {
            pre = nums[pre];
            pre1 = nums[pre1];
        }
        return pre1;
    }
}
