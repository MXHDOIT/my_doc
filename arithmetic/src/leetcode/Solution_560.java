package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组不是单调的话，不要用滑动窗口，考虑用前缀和.
 *
 * @author: maxinhang.
 */
public class Solution_560 {

    public int subarraySum(int[] nums, int k) {
        int s = 0;
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        for (int num : nums) {
            s += num;
            ans += cnt.getOrDefault(s - k, 0);
            cnt.merge(s, 1, Integer::sum);
        }
        return ans;
    }

    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : prefix) {
            ans = cnt.getOrDefault(num - k, 0);
            cnt.merge(num, 1, Integer::sum);
        }
        return ans;
    }

    public int subarraySum1(int[] nums, int k) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    result++;
                }
            }
        }
        return result;
    }
}
