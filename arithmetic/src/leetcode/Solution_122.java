package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_122 {


    public int maxProfitDp(int[] prices) {
        int n = prices.length;
        // 在第n天持有/不持有股票第收益
        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    public int maxProfit(int[] prices) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int price : prices) {
            if (!stack.isEmpty() && stack.peek() <= price) {
                ans += price - stack.pop();
            }
            stack.push(price);
        }
        return ans;
    }
}
