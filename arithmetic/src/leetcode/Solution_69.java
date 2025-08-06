package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_69 {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ans = -1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
