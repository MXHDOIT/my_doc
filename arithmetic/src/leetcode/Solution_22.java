package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_22 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(result, 0, 0, "", n);
        return result;
    }

    public void dfs(List<String> result, int leftSize, int rightSize, String temp, int n) {
        if (temp.length() == n * 2) {
            result.add(temp);
            return;
        }
        if (leftSize > rightSize) {
            if (leftSize < n) {
                //左
                dfs(result, leftSize + 1, rightSize, temp + "(", n);
            }
            //右
            dfs(result, leftSize, rightSize + 1, temp + ")", n);
        } else {
            //左
            dfs(result, leftSize + 1, rightSize, temp + "(", n);
        }
    }

    public static void main(String[] args) {
        List<String> strings = new Solution_22().generateParenthesis(3);
        System.out.println(strings);
    }
}
