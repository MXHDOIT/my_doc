package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: maxinhang.
 */
public class Solution_567 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            need.merge(ch, 1, Integer::sum);
        }
        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < s2.length()) {
            char ch = s2.charAt(right);
            if (need.containsKey(ch)) {
                window.merge(ch, 1, Integer::sum);
                if (window.get(ch).equals(need.get(ch))) {
                    valid++;
                }
            }
            right++;
            while (right - left >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char charAt = s2.charAt(left);
                left++;
                if (window.containsKey(charAt)) {
                    if (window.get(charAt).equals(need.get(charAt))) {
                        valid--;
                    }
                    window.put(charAt, window.get(charAt) - 1);
                }
            }
        }
        return false;
    }
}
