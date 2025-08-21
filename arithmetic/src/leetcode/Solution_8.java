package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/8/21 22:46.
 */
public class Solution_8 {
    public int myAtoi(String s) {
        int ans = 0;
        int bndry = Integer.MAX_VALUE / 10;
        int i = 0;
        int sign = 1;
        int length = s.length();
        while (s.charAt(i) == ' ') {
            if (++i == length) return 0;
        }
        if (s.charAt(i) == '-') sign = -1;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') i++;

        for (int j = i; j < length; j++) {
            char ch = s.charAt(j);
            if (ch < '0' || ch > '9') break;
            if (ans > bndry || ans == bndry && ch > '7')
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            ans = ans * 10 + (ch - '0');
        }
        return ans * sign;
    }
}
