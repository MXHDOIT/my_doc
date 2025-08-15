package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: maxinhang.
 */
public class Solution_763 {
    public List<Integer> partitionLabels(String s) {
        //统计每一个字符最后出现的位置
        char[] chs = s.toCharArray();
        int n = chs.length;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(chs[i], i);
        }

        //从头遍历字符，并更新字符的最远出现下标，如果找到字符最远出现位置下标和当前下标相等了，则找到了分割点
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
            right = Math.max(right, map.get(chs[i]));
            if (right == i) {
                ans.add(right - left + 1);
                left = i + 1;
            }
        }
        return ans;
    }
}
