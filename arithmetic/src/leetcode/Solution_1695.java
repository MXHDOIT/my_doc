package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: maxinhang.
 */
public class Solution_1695 {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int max = 0;
        int left = 0;
        int current = 0;
        Map<Integer, Integer> num2Index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num2Index.containsKey(num) && num2Index.get(num) >= left) {
                Integer index = num2Index.get(num);
                for (int j = left; j <= index; j++) {
                    current -= nums[j];
                }
                left = index + 1;
            }
            num2Index.put(num, i);
            current += num;
            max = Math.max(current, max);
        }
        return max;
    }
}
