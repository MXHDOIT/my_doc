package leetcode;

import java.util.Arrays;

/**
 * @author: maxinhang.
 */
public class Solution_16 {
    public static void main(String[] args) {
        int sumClosest = new Solution_16().threeSumClosest(new int[]{0,0,0}, 1);
        System.out.println(sumClosest);
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            int num = nums[i];
            int left = i + 1;
            int right = n - 1;

            int sum = num + nums[left] + nums[left + 1];
            if (sum > target) {
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                continue;
            }

            sum = num + nums[right] + nums[right - 1];
            if (sum < target) {
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                continue;
            }

            while (left < right) {
                sum = nums[left] + nums[right] + num;
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    return ans;
                }
            }

            while (i < n - 2 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return ans;
    }
}
