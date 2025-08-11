package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: maxinhang.
 */
public class Solution_994 {

    public static void main(String[] args) {
        new Solution_994().orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
    }

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int r = grid.length;
        int c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int row = poll[0];
                int col = poll[1];
                //上
                if ((row - 1) >= 0 && grid[row - 1][col] == 1) {
                    grid[row - 1][col] = 2;
                    queue.add(new int[]{row - 1, col});
                }
                //下
                if ((row + 1) < r && grid[row + 1][col] == 1) {
                    grid[row + 1][col] = 2;
                    queue.add(new int[]{row + 1, col});
                }
                //左
                if ((col - 1) >= 0 && grid[row][col - 1] == 1) {
                    grid[row][col - 1] = 2;
                    queue.add(new int[]{row, col - 1});
                }
                //右
                if ((col + 1) < c && grid[row][col + 1] == 1) {
                    grid[row][col + 1] = 2;
                    queue.add(new int[]{row, col + 1});
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return ans == 0 ? 0 : ans - 1;
    }
}
