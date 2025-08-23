package leetcode;

import java.util.Objects;

/**
 * @author: maxinhang.
 * @version: 2025/8/23 22:01.
 */
public class Solution_115 {

    public static void main(String[] args) {
        new Solution_115().multiply("9", "99");
    }

    public String multiply(String num1, String num2) {
        if (Objects.equals(num1, "0") || Objects.equals(num2, "0")) {
            return "0";
        }
        String ans = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            ans = add(ans, temp.reverse().toString());
        }
        return ans;
    }

    private String add(String num1, String num2) {
        int n = num1.length() - 1;
        int m = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (n >= 0 || m >= 0 || carry > 0) {
            int nNum = 0;
            if (n >= 0) {
                nNum = num1.charAt(n) - '0';
                n--;
            }
            int mNum = 0;
            if (m >= 0) {
                mNum = num2.charAt(m) - '0';
                m--;
            }
            int sum = nNum + mNum + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }
}
