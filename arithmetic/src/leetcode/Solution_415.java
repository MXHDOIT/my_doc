package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_415 {
    public String addStrings(String num1, String num2) {
        int num1Index = num1.length() - 1;
        int num2Index = num2.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();

        while (num1Index >= 0 || num2Index >= 0 || carry > 0) {
            int digit1 = (num1Index >= 0) ? num1.charAt(num1Index) - '0' : 0;
            int digit2 = (num2Index >= 0) ? num2.charAt(num2Index) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.insert(0, sum % 10);
            num1Index--;
            num2Index--;
        }

        return result.toString();

    }
}
