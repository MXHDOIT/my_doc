package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        // text1 n text2 m 结尾最长长度
        int[][] dp = new int[n + 1][m + 1];

        // 正确初始化第一行和第一列
        for (int i = 1; i <= n; i++) {
            if (text1.charAt(i - 1) == text2.charAt(0)) {
                dp[i][1] = 1;
            } else {
                dp[i][1] = dp[i - 1][1];
            }
        }
        for (int i = 1; i <= m; i++) {
            if (text2.charAt(i - 1) == text1.charAt(0)) {
                dp[1][i] = 1;
            } else {
                dp[1][i] = dp[1][i - 1];
            }
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        int i = new Solution_1143().longestCommonSubsequence("abcde", "ace");
        System.out.println(i);
    }
}
