package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_3354 {
    public int countValidSelections(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int ans = 0;
        int prev = 0;
        for (int num : nums) {
            if (num > 0) {
                prev += num;
            } else if (prev * 2 == total) {
                ans += 2;
            } else if (Math.abs(prev*2 - total) == 1){
                ans += 1;
            }
        }
        return ans;
    }
}
