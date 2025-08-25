package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_151 {

    public static void main(String[] args) {
        new Solution_151().reverseWords("a good   example");
    }

    public String reverseWordsOpt(String s) {
        String[] split = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        int n = split.length;
        for (int i = n - 1; i >= 0; i--) {
            String s1 = split[i];
            if (s1.length() == 0) {
                continue;
            }
            stringBuilder.append(s1);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        int n = split.length;
        for (int i = n - 1; i >= 0; i--) {
            String s1 = split[i];
            if (s1.length() == 0) {
                continue;
            }
            stringBuilder.append(s1);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
