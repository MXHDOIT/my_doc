package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] < target) {
                i++;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean b = new Solution_240().searchMatrix(new int[][]{{1, 1}}, 1);
    }
}
