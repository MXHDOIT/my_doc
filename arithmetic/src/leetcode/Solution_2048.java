package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_2048 {
    public int nextBeautifulNumber(int n) {
        for (int i = n + 1; i < Math.pow(10, 6); i++) {
            if (isBeautifulNumber(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isBeautifulNumber(int num) {
        int[] count = new int[10];

        while (num > 0) {
            count[num % 10]++;
            num /= 10;
        }

        for (int i = 0; i < 10; i++) {
            if (count[i] > 0 && count[i] != i) {
                return false;
            }
        }
        return true;
    }
}
