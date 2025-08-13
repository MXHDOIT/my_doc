package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_42 {

    public int trapStack(int[] height) {
        int n = height.length;
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int cHeight = height[i];
            while (!stack.isEmpty() && cHeight > height[stack.peek()]) {
                Integer index = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int dh = Math.min(height[stack.peek()], cHeight) - height[index];
                result += dh * (i - stack.peek() - 1);
            }
            stack.push(i);
        }

        return result;
    }

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
