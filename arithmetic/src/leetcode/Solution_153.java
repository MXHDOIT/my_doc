package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/8/10 19:31.
 */
public class Solution_153 {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int sIndex, int eIndex) {
        if (nums[sIndex] <= nums[eIndex]) {
            return nums[sIndex];
        }
        int mid = (sIndex + eIndex) / 2;

        if (nums[mid] > nums[eIndex]) {
            return findMin(nums, mid + 1, eIndex);
        } else {
            return findMin(nums, sIndex, mid);
        }
    }
}
