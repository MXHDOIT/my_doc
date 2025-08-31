package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/8/31 10:54.
 */
public class Solution_162 {

    public int findPeakElementOpt(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                // 中间元素大于右边元素，说明峰值在左边（包括mid）
                right = mid;
            } else {
                // 中间元素小于右边元素，说明峰值在右边
                left = mid + 1;
            }
        }
        return left;
    }

    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean left;
            boolean right;
            if (i == 0) {
                left = true;
            } else {
                left = nums[i] > nums[i - 1];
            }
            if (i == nums.length - 1) {
                right = true;
            } else {
                right = nums[i] > nums[i + 1];
            }
            if (left && right) {
                return i;
            }
        }
        return -1;
    }
}
