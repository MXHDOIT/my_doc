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

    public static void main(String[] args) {
        new Solution_76().minWindow("ADOBECODEBANC", "ABC");
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.merge(ch, 1, Integer::sum);
        }
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int valid = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (need.containsKey(ch)) {
                //入
                window.merge(ch, 1, Integer::sum);
                if (Objects.equals(window.get(ch), need.get(ch))) {
                    valid++;
                }
            }
            right++;
            //出
            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char c = s.charAt(left);
                left++;
                if (window.containsKey(c)) {
                    window.merge(c, -1, Integer::sum);
                    if (window.get(c) < need.get(c)) {
                        valid--;
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
