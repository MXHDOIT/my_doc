package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_33 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int num = nums[mid];
            if (num == target) {
                return mid;
            }

            if (num >= nums[left]) {
                if (num > target && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (num < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
