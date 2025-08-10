package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/8/10 14:20.
 */
public class Solution_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            int num = matrix[row][col];
            if (num > target) {
                col--;
            } else if (num < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
}
