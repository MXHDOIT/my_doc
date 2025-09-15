package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_498 {

    public int[] findDiagonalOrderOpt1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];
        int tier = m + n - 1;
        int index = 0;
        for (int i = 0; i < tier; i++) {
            if (i % 2 == 0) {
                //从下向上
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    ans[index] = mat[x--][y++];
                }
            } else {
                //从上向下
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    ans[index] = mat[x++][y--];
                }
            }
        }
        return ans;
    }

    public int[] findDiagonalOrderOpt(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] ans = new int[n * m];
        int index = 0;
        for (int i = 0; i < n + m - 1; i++) {
            if (i % 2 == 0) {
                int x = i < n ? i : n - 1;
                int y = i < n ? 0 : i - n + 1;
                while (x >= 0 && y < m) {
                    ans[index++] = mat[x][y];
                    x--;
                    y++;
                }
            } else {
                int x = i < m ? 0 : i - m + 1;
                int y = i < m ? i : m - 1;
                while (x < n && y >= 0) {
                    ans[index++] = mat[x][y];
                    x++;
                    y--;
                }
            }
        }
        return ans;
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int length = n + m - 1;
        List<List<Integer>> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                list.get(i + j).add(mat[i][j]);
            }
        }
        int[] ans = new int[n * m];
        boolean reverse = true;
        int index = 0;
        for (List<Integer> nums : list) {
            if (reverse) {
                Collections.reverse(nums);
            }
            for (Integer num : nums) {
                ans[index++] = num;
            }
            reverse = !reverse;
        }
        return ans;
    }
}
