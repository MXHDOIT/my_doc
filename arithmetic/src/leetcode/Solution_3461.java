package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/10/23 22:42.
 */
public class Solution_3461 {
    public boolean hasSameDigits(String s) {
        char[] chars = s.toCharArray();

        while (chars.length > 2) {
            char[] tempChars = new char[chars.length - 1];
            for (int i = 1; i < chars.length; i++) {
                tempChars[i - 1] = (char) (((chars[i - 1] - '0') + (chars[i] - '0')) % 10 + '0');
            }
        }
        return chars[0] == chars[1];
    }
}
