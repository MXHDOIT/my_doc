package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: maxinhang.
 */
public class Solution_91 {

    public static void main(String[] args) {
        int decodings = new Solution_91().numDecodingsDp("226");
        System.out.println(decodings);
    }

    public int numDecodingsDp(String s) {
        Set<String> encodings = new HashSet<>();
        for (int i = 1; i <= 26; i++) {
            encodings.add(String.valueOf(i));
        }
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        if (encodings.contains(s.substring(0, 1))) {
            dp[1] = 1;
        }
        for (int i = 1; i < length; i++) {
            if (encodings.contains(s.substring(i, i + 1))) {
                dp[i + 1] += dp[i];
            }
            if (encodings.contains(s.substring(i - 1, i + 1))) {
                dp[i + 1] += dp[i - 1];
            }
        }

        return dp[length];
    }

    public int numDecodings(String s) {
        Set<String> encodings = new HashSet<>();
        for (int i = 1; i <= 26; i++) {
            encodings.add(String.valueOf(i));
        }
        int dfs = dfs(s, encodings, 0, s.length());
        return dfs;
    }

    public int dfs(String s, Set<String> encodings, int curIndex, int length) {
        if (curIndex == length) {
            return 1;
        }
        int ans = 0;
        if (encodings.contains(s.substring(curIndex, curIndex + 1))) {
            ans += dfs(s, encodings, curIndex + 1, length);
        }
        if (curIndex + 1 < length && encodings.contains(s.substring(curIndex, curIndex + 2))) {
            ans += dfs(s, encodings, curIndex + 2, length);
        }
        return ans;
    }
}
