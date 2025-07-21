package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_1957 {
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        sb.append(chars[0]);
        int size = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                size++;
            } else {
                size = 1;
            }

            if (size < 3) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}
