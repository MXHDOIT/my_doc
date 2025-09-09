package leetcode;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_402 {
    public static void main(String[] args) {
        Solution_402 solution = new Solution_402();
        solution.removeKdigits("10200", 1);
    }

    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (n == k) {
            return "0";
        }
        Deque<Character> deque = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char digit = num.charAt(i);
            while (k > 0 && !deque.isEmpty() && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }
        while (k-- > 0) {
            deque.pollLast();
        }
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }
}
