package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int left = 0; left < n - 2; left++) {
            if (left > 0 && nums[left] == nums[left - 1]) {
                continue;
            }
            int leftNum = nums[left];
            for (int mid = left + 1; mid < n - 1; mid++) {
                if (mid > left + 1 && nums[mid] == nums[mid - 1]) {
                    continue;
                }
                int midNum = nums[mid];
                int rightNum = -(leftNum + midNum);
                int right = n - 1;
                while (mid < right && nums[right] > rightNum) {
                    --right;
                }
                if (mid == right) {
                    break;
                }
                if (nums[right] == rightNum) {
                    List<Integer> list = new ArrayList<>();
                    list.add(leftNum);
                    list.add(midNum);
                    list.add(rightNum);
                    result.add(list);
                }
            }
        }
        return result;
    }

}
