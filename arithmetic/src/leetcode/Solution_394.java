package leetcode;

import java.util.Stack;

/**
 * @author: maxinhang.
 */
public class Solution_394 {
    public static void main(String[] args) {
        Solution_394 solution394 = new Solution_394();
        String string = solution394.decodeStringOpt("20[abc]3[cd]ef");
        System.out.println(string);
    }

    public String decodeStringOpt(String s) {
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == ']') {
                //字符串
                StringBuilder sb = new StringBuilder();
                while (stack.peek() != '[') {
                    sb.insert(0, stack.pop());
                }
                //推出括号
                stack.pop();
                //前缀数据
                int num = 0;
                int index = 0;
                while (!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9') {
                    num += (stack.pop() - '0') * Math.pow(10, index);
                    index++;
                }
                //重新入栈
                char[] chs = sb.toString().toCharArray();
                for (int i = 0; i < num; i++) {
                    for (char c : chs) {
                        stack.push(c);
                    }
                }
            } else {
                stack.push(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
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
