package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: maxinhang.
 * @version: 2025/8/30 17:19.
 */
public class Solution_128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (Integer num : set) {
            //如果不是起始数字，直接退出
            if (set.contains(num - 1)) {
                continue;
            }
            //找到最后一个数
            int y = num + 1;
            while (set.contains(y)) {
                y++;
            }
            ans = Math.max(ans, y - num);
            if (ans * 2 >= set.size()) {
                break;
            }
        }
        return ans;
    }
}
