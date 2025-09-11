package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_11 {

    //left++和right--都是为了尝试取到更多的水，如果短的板不动的话，取到的水永远不会比上次多
    public int maxAreaOpt(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int ans = 0;

        while (left < right) {
            ans = Math.max(ans, (right - left) * Math.min(height[right], height[left]));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

    public int maxArea(int[] height) {
        int n = height.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans = Math.max(ans, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return ans;
    }
}
