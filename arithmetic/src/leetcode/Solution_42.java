package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_42 {
    public int trap(int[] height) {
        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (height[i] < min) {
                result += (min - height[i]);
            }
        }
        return result;
    }
}
