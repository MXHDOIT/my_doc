package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '{' || ch == '(' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.empty()) {
                    return false;
                }
                Character peek = stack.pop();
                if ((peek == '{' && ch != '}') || (peek == '(' && ch != ')') || (peek == '[' && ch != ']')) {
                    return false;
                }
            }
        }
        return stack.empty();
    }
}
