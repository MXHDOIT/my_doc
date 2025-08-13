package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_326 {
    public boolean isPowerOfThree(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
