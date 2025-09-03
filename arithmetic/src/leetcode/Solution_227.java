package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_227 {

    public static void main(String[] args) {
        new Solution_227().calculate("1-1+1");
    }

    public int calculate(String s) {
        int n = s.length();
        Stack<Integer> numStack = new Stack<>();
        char lastOperation = '+';
        int num = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                if (lastOperation == '+') {
                    numStack.push(num);
                } else if (lastOperation == '-') {
                    numStack.push(-num);
                } else if (lastOperation == '*') {
                    numStack.push(numStack.pop() * num);
                } else {
                    numStack.push(numStack.pop() / num);
                }
                lastOperation = c;
                num = 0;
            }
        }

        while (!numStack.isEmpty()) {
            num += numStack.pop();
        }
        return num;
    }
}
