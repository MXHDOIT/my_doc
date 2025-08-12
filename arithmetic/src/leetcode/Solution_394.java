package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_394 {
    public static void main(String[] args) {
        Solution_394 solution394 = new Solution_394();
        String string = solution394.decodeString("20[abc]3[cd]ef");
        System.out.println(string);
    }

    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == '[' || (ch >= '0' && ch <= '9')) {
                stack.push(ch);
            } else if (ch == ']') {
                StringBuilder chars = new StringBuilder();
                while ('[' != stack.peek()) {
                    chars.insert(0, stack.pop());
                }
                stack.pop();
                StringBuilder nums = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() > '0' && stack.peek() <= '9') {
                    nums.insert(0, stack.pop());
                }
                Integer size = Integer.valueOf(nums.toString());
                if (stack.isEmpty()) {
                    for (int i = 0; i < size; i++) {
                        sb.append(chars);
                    }
                } else {
                    char[] charArray = chars.toString().toCharArray();
                    for (int i = 0; i < size; i++) {
                        for (char c : charArray) {
                            stack.push(c);
                        }
                    }
                }
            } else {
                if (stack.isEmpty()) {
                    sb.append(ch);
                } else {
                    stack.push(ch);
                }
            }
        }
        return sb.toString();
    }
}
