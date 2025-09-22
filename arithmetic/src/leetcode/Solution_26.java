package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_26 {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int index = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i-1]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
