package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_1277 {
    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    if (matrix[i][j] == 1) {
                        dp[i][j] = 1;
                    }
                } else {
                    if (matrix[i][j] == 1) {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    }
                }
                ans += dp[i][j];
            }
        }
        return ans;
    }
}
