package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_3195 {
    public int minimumArea(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int top = n;
        int bottom = -1;
        int left = m;
        int right = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    top = Math.min(top, i);
                    bottom = Math.max(bottom, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }

        return (bottom - top + 1) * (right - left + 1);
    }
}
