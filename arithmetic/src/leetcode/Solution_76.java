package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 滑动窗口.
 *
 * @author: maxinhang.
 */
public class Solution_76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.merge(ch, 1, Integer::sum);
        }

        int valid = 0;
        int left = 0;
        int right = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (right < s.length()) {
            //入
            char ch = s.charAt(right);
            if (need.containsKey(ch)) {
                window.merge(ch, 1, Integer::sum);
                if (Objects.equals(window.get(ch), need.get(ch))) {
                    valid++;
                }
            }
            right++;
            //出
            while (need.size() == valid) {
                if (right - left <= len) {
                    start = left;
                    len = right - left;
                }
                char charAt = s.charAt(left);
                left++;
                if (window.containsKey(charAt)) {
                    Integer size = window.get(charAt);
                    size = size - 1;
                    window.put(charAt, size);
                    if (size < need.get(charAt)) {
                        valid--;
                    }
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
