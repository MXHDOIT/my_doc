package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/10/29 19:04.
 */
public class Solution_didi_2 {

    //一个随机正整数数组，一个目标值，找出其和>=目标值的最短连续子数组
    public int[] subArr(int[] arr, int target) {
        int n = arr.length;
        int resultLeftIndex = 0;
        int minLength = n + 1;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < n) {
            sum += arr[right];
            while (sum >= target) {
                int len = right - left + 1;
                if (len < minLength) {
                    minLength = len;
                    resultLeftIndex = left;
                }
                sum -= arr[left];
                left++;
            }
            right++;
        }
        if (minLength == n+1) {
            return new int[]{};
        }
        int[] ans = new int[minLength];
        for (int i = 0; i < minLength; i++) {
            ans[i] = arr[i + resultLeftIndex];
        }
        return ans;
    }
}
