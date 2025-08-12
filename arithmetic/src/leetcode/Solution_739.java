package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_739 {
    /**
     * 单调栈存索引.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperature) {
                Integer pop = stack.pop();
                ans[pop] = i - pop;
            }
            stack.push(i);
        }
        return ans;
    }
}
