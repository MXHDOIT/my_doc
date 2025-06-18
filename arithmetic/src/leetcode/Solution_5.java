package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_5 {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int startIndex = 0;
        int endIndex = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i + 1 < j - 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j]) {
                    if (j - i > endIndex - startIndex) {
                        startIndex = i;
                        endIndex = j;
                    }
                }
            }
        }
        return s.substring(startIndex, endIndex + 1);
    }
}
