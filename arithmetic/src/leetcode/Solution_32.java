package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_32 {

    public static void main(String[] args) {
        new Solution_32().longestValidParentheses("(()");
    }

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            char ch = chars[i];
            if (ch == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty() || stack.peek() == -1 || chars[stack.peek()] == ')') {
                    stack.push(i);
                } else {
                    stack.pop();
                }
            }
        }
        int max = 0;
        int last = n;
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            max = Math.max(max, last - index - 1);
            last = index;
        }
        return max;
    }
}
