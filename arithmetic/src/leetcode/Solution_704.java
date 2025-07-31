package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_704 {
    public int search(int[] nums, int target) {
        int right = nums.length - 1;
        int left = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int num = nums[mid];
            if (num > target) {
                right = mid - 1;
            } else if (num < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
