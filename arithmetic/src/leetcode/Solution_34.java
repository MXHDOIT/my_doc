package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_34 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        int[] ans = new int[2];
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                int index = mid;
                while (index >= 0 && nums[index] == target) {
                    ans[0] = index;
                    index--;
                }
                index = mid;
                while (index < nums.length && nums[index] == target) {
                    ans[1] = index;
                    index++;
                }
            }
        }
        return ans;
    }
}
