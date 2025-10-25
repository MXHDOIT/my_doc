package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/10/25 21:43.
 */
public class Solution_1716 {
    public int totalMoney(int n) {
        int weeks = n / 7;
        int days = n % 7;

        int result = 0;
        for (int i = 0; i < weeks; i++) {
            result += 28 + 7 * i;
        }
        for (int i = 0; i < days; i++) {
            result += weeks + 1 + i;
        }
        return result;
    }
}
