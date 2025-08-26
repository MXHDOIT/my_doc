package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_3000 {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int area = 0;
        int maxDiagonal = 0;
        for (int i = 0; i < dimensions.length; i++) {
            int width = dimensions[i][0];
            int height = dimensions[i][1];
            if (width * width + height * height > maxDiagonal
                    || (width * width + height * height == maxDiagonal
                    && width * height > area)) {
                maxDiagonal = width * width + height * height;
                area = width * height;
            }
        }
        return area;
    }
}
