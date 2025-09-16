package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_123 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        //第i天结束，交易j次，持有/不持有的最大收益
        int[][][] dp = new int[n][3][2];
        for (int i = 0; i < 3; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (j > 0) {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                } else {
                    dp[i][j][0] = dp[i - 1][j][0];
                }

                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i]);
            }
        }
        return dp[n - 1][2][0];
    }
}
