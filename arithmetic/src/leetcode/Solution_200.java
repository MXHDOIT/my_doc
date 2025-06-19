package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: maxinhang.
 */
public class Solution_200 {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] parent = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    if (i + 1 < n && grid[i + 1][j] == '1') {
                        union(genIndex(i, j, m), genIndex(i + 1, j, m), parent);
                    }
                    if (j + 1 < m && grid[i][j + 1] == '1') {
                        union(genIndex(i, j, m), genIndex(i, j + 1, m), parent);
                    }
                }
            }
        }

        Set<Integer> isLandRoots = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    isLandRoots.add(find(genIndex(i, j, m), parent)); // ⚠️ 记得用 find()
                }
            }
        }
        return isLandRoots.size();
    }

    private void union(int x, int y, int[] parent) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }

    // 列数是 m，不是 n
    private int genIndex(int i, int j, int m) {
        return i * m + j;
    }
}
