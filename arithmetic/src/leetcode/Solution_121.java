package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int maxDiff = 0;
        int min =Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > maxDiff) {
                maxDiff = prices[i] - min;
            }
        }
        return maxDiff;
    }
}
