package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_93 {

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) return result;
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> current, List<String> result) {
        // 当已分割4段且遍历完所有字符时
        if (current.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        // 剩余字符数过少或过多的剪枝
        int remaining = s.length() - start;
        if (remaining < (4 - current.size()) || remaining > (4 - current.size()) * 3) {
            return;
        }

        // 尝试分割1-3位数字
        for (int i = 1; i <= 3 && start + i <= s.length(); i++) {
            String segment = s.substring(start, start + i);
            if (isValidSegment(segment)) {
                current.add(segment);
                backtrack(s, start + i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isValidSegment(String segment) {
        int len = segment.length();
        if (len == 0 || len > 3) return false;
        if (len > 1 && segment.charAt(0) == '0') return false;

        int num = 0;
        for (char c : segment.toCharArray()) {
            num = num * 10 + (c - '0');
        }
        return num >= 0 && num <= 255;
    }
}
