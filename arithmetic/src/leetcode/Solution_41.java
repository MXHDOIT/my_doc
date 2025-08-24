package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/8/24 11:22.
 */
public class Solution_41 {

    public int firstMissingPositiveOpt(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int j = nums[i] - 1;
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        boolean[] exist = new boolean[length + 2];
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (num <= length && num >= 0) {
                exist[num] = true;
            }
        }
        for (int i = 1; i <= length + 1; i++) {
            if (!exist[i]) {
                return i;
            }
        }
        return -1;
    }
}
