package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_169 {
    public int majorityElement(int[] nums) {
        int high = 0;
        int size = 0;
        for (int num : nums) {
            if (size == 0) {
                high = num;
                size = 1;
            } else {
                if (num == high) {
                    size++;
                } else {
                    size--;
                }
            }
        }
        return high;
    }
}
