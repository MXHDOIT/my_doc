package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    temp.add(1);
                } else {
                    List<Integer> list = ans.get(i - 1);
                    temp.add(list.get(j) + list.get(j - 1));
                }
            }
            ans.add(temp);
        }
        return ans;
    }
}
