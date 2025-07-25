package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: maxinhang.
 */
public class Solution_3487 {
    public int maxSum(int[] nums) {
        Set<Integer> exist = new HashSet<>();
        int max = nums[0];
        exist.add(nums[0]);
        for (int num : nums) {
            if (exist.contains(num)) {
                continue;
            }
            int temp = max + num;
            if (temp > max) {
                max = temp;
                exist.add(num);
            }
            if (num > max){
                max = num;
                exist.clear();
                exist.add(num);
            }
        }
        return max;
    }
}
