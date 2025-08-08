package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_136 {
    /**
     * 异或：相同为零，不同为1.
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
