package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_79 {

    public static void main(String[] args) {
        Solution_79 solution = new Solution_79();

        solution.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCED");
    }
    public boolean exist(char[][] board, String word) {
        //优化一：字符元素Check
        //优化二：首尾字符选择
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        int length = word.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(board, visited, i, j, n, m, word, 0, length)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited,
                        int i, int j, int n, int m,
                        String word, int index, int length) {
        if (index == length) {
            return true;
        }
        if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }
        visited[i][j] = true;
        boolean ans = dfs(board, visited, i - 1, j, n, m, word, index + 1, length)
                || dfs(board, visited, i + 1, j, n, m, word, index + 1, length)
                || dfs(board, visited, i, j - 1, n, m, word, index + 1, length)
                || dfs(board, visited, i , j+ 1, n, m, word, index + 1, length);
        visited[i][j] = false;
        return ans;
    }
}
