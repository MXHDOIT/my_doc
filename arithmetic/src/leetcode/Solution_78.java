package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 * @version: 2025/8/9 17:05.
 */
public class Solution_78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        subsets(nums,0,new ArrayList<>(),ans);
        return ans;
    }

    public void subsets(int[] nums, int index, List<Integer> temp, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[index]);
        subsets(nums, index + 1, temp, ans);
        temp.remove(temp.size()-1);
        subsets(nums, index + 1, temp, ans);
    }
}
