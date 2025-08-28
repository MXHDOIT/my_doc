package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_39 {

    public static void main(String[] args) {
        new Solution_39().combinationSum(new int[]{2, 3, 6, 7}, 7);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, target, 0, ans, new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(int[] candidates, int target, int index,
                     List<List<Integer>> ans, List<Integer> nums, int sum) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            ans.add(new ArrayList<>(nums));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            nums.add(candidate);
            dfs(candidates, target, i, ans, nums, sum + candidate);
            nums.remove(nums.size() - 1);
        }
    }
}
