package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_279 {
    public int numSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int num = 1;
        while (num * num <= n) {
            squares.add(num * num);
            num++;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < squares.size(); j++) {
                if (i >= dp[j]) {
                    dp[i] = Math.min(dp[i - dp[j]] + 1, dp[i]);
                }
            }
        }
        return dp[n] == n + 1 ? -1 : dp[n];
    }
}
