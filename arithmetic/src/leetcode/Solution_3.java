package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: maxinhang.
 */
public class Solution_3 {

    /**
     * 滑动窗口, 简单说就是把重复的字母及在此之前的踢出队列.
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> ch2Index = new HashMap<>();
        int max = 0;
        int startIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch2Index.containsKey(ch) && ch2Index.get(ch) >= startIndex) {
                startIndex = ch2Index.get(ch) + 1;
            }
            max = Math.max(i - startIndex + 1, max);
            ch2Index.put(ch, i);
        }
        return max;
    }

}
