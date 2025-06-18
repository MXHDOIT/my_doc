package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: maxinhang.
 */
public class Solution_1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> num2IndexMap = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            Integer existDiff = num2IndexMap.get(diff);
            if (existDiff != null) {
                result[0] = existDiff;
                result[1] = i;
                break;
            }
            num2IndexMap.put(nums[i], i);
        }
        return result;
    }
}
