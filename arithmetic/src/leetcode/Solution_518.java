package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_518 {

    public static void main(String[] args) {
        int change = new Solution_518().changeOpt(5, new int[]{1, 2, 5});
        System.out.println(change);
    }

    public int changeOpt(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            // 再遍历金额，从硬币面值开始
            for (int i = coin; i <= amount; i++) {
                // 累加使用当前硬币的组合数
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    public int change(int amount, int[] coins) {
        return dfs(amount, coins, 0, 0);
    }

    private int dfs(int amount, int[] coins, int index, int sum) {
        if (sum > amount) {
            return 0;
        }
        if (sum == amount) {
            return 1;
        }
        int ans = 0;
        for (int i = index; i < coins.length; i++) {
            int dfs = dfs(amount, coins, i, sum + coins[i]);
            ans += dfs;
        }
        return ans;
    }
}
