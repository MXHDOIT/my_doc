package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_179 {
    public String largestNumber(int[] nums) {
        List<String> numStrings = new ArrayList<>(nums.length);
        for (int num : nums) {
            numStrings.add(String.valueOf(num));
        }
        numStrings.sort((a, b) -> {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        });
        // 如果排序后的第一个元素是0，说明整个数组都是0，直接返回"0"
        if (numStrings.get(0).equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String numString : numStrings) {
            sb.append(numString);
        }
        return sb.toString();
    }
}
