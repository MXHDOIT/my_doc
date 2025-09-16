package leetcode;

import java.util.Arrays;

/**
 * @author: maxinhang.
 */
public class Solution_188 {

    public int maxProfitOpt(int k, int[] prices) {
        int n = prices.length;
        // dp[i][j]表示第i天结束时，进行了j笔交易的最大利润
        // 状态0：不持有股票，状态1：持有股票
        int[][][] dp = new int[n][k + 1][2];

        // 初始化第一天的状态
        for (int j = 0; j <= k; j++) {
            dp[0][j][0] = 0;              // 第一天不持有股票，利润为0
            dp[0][j][1] = -prices[0];     // 第一天持有股票，利润为负的股价
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                // 第i天不持有股票的情况：
                // 1. 前一天也不持有股票
                // 2. 前一天持有股票，今天卖出
                if (j > 0) {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                } else {
                    dp[i][j][0] = dp[i - 1][j][0];
                }

                // 第i天持有股票的情况：
                // 1. 前一天也持有股票
                // 2. 前一天不持有股票，今天买入
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] + prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

    int[] prices;
    int[][][] memo;

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        //n天 交易k次 持有/不持有
        memo = new int[n][k + 1][2];
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }

        return dfs(n - 1, k, 0);
    }

    private int dfs(int i, int j, int hold) {
        if (j < 0) {
            return Integer.MIN_VALUE / 2;
        }
        if (i < 0) {
            return hold == 1 ? Integer.MIN_VALUE / 2 : 0;
        }
        if (memo[i][j][hold] != -1) {
            return memo[i][j][hold];
        }
        if (hold == 1) {
            return memo[i][j][hold] = Math.max(dfs(i - 1, j, 1), dfs(i - 1, j, 0) - prices[i]);
        }

        return memo[i][j][hold] = Math.max(dfs(i - 1, j, 0), dfs(i - 1, j - 1, 1) + prices[i]);
    }
}
