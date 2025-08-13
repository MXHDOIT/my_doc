package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_84 {

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int area = new Solution_84().largestRectangleArea(heights);
        System.out.println(area);
    }

    public int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE;
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i <= n; i++) {
            int cHeight = i == n ? -1 : heights[i];
            while (stack.size() > 1 && cHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int left = stack.peek();
                max = Math.max(max, (i - left - 1) * height);
            }
        }
        return max;
    }

    public int largestRectangleArea1(int[] heights) {
        int max = Integer.MIN_VALUE;
        int n = heights.length;

        for (int i = 0; i < n; i++) {
            int leftIndex = i - 1;
            while (leftIndex >= 0 && heights[leftIndex] >= heights[i]) {
                leftIndex--;
            }
            int rightIndex = i + 1;
            while (rightIndex < n && heights[rightIndex] >= heights[i]) {
                rightIndex++;
            }
            max = Math.max(max, (rightIndex - leftIndex - 1) * heights[i]);
        }
        return max;
    }
}
