package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_1717 {
    public int maximumGain(String s, int x, int y) {
        char[] chars = s.toCharArray();
        if (x >= y) {
            return helper(chars, 'a', 'b', x, y);
        } else {
            return helper(chars, 'b', 'a', y, x);
        }
    }

    private int helper(char[] chars, char first, char second, int firstScore, int secondScore) {
        int score = 0;
        Stack<Character> stack = new Stack<>();
        for (char ch : chars) {
            if (stack.isEmpty() || ch != second) {
                stack.push(ch);
            } else {
                Character peek = stack.peek();
                if (peek == first) {
                    stack.pop();
                    score += firstScore;
                }else {
                    stack.push(ch);
                }
            }
        }

        Stack<Character>  remaining = new Stack<>();
        while (!stack.isEmpty()) {
            Character ch = stack.pop();
            if (remaining.isEmpty() || ch != second) {
                remaining.push(ch);
            } else {
                Character peek = remaining.peek();
                if (peek == first) {
                    remaining.pop();
                    score += secondScore;
                }else {
                    remaining.push(ch);
                }
            }
        }
        return score;
    }
}
