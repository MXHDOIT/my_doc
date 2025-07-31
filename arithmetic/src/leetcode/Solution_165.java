package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_165 {
    public int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int length1 = split1.length;
        int length2 = split2.length;
        int maxLength = Math.max(length1, length2);
        for (int i = 0; i < maxLength; i++) {
            int num1 = 0;
            int num2 = 0;
            if (i < length1) {
                num1 = toNumber(split1[i]);
            }
            if (i < length2) {
                num2 = toNumber(split2[i]);
            }
            if (num1 < num2) {
                return -1;
            }else if (num1 > num2) {
                return 1;
            }
        }
        return 0;
    }

    private int toNumber(String s) {
        int num = 0;
        for (char c : s.toCharArray()) {
            num = num * 10 + (c - '0');
        }
        return num;
    }

    public static void main(String[] args) {
        int i = new Solution_165().compareVersion("1.0", "1.0.0.0");
        System.out.println(i);
    }
}
