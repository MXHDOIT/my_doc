package leetcode;

import java.util.Arrays;

/**
 * @author: maxinhang.
 * @version: 2025/8/4 21:24.
 */
public class Solution_31 {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            int currentNum = nums[i];
            int nextNum = Integer.MAX_VALUE;
            int nextIndex = -1;
            for (int j = i + 1; j < n; j++) {
                int num = nums[j];
                if (num > currentNum && num < nextNum) {
                    nextNum = num;
                    nextIndex = j;
                }
            }
            if (nextIndex != -1) {
                nums[nextIndex] = nums[i];
                nums[i] = nextNum;
                Arrays.sort(nums, i + 1, n);
                return;
            }
        }
        Arrays.sort(nums);
    }
}
