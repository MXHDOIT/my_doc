package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: maxinhang.
 * @version: 2025/9/10 10:00.
 */
public class Solution_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        List<Integer> temp = new ArrayList<>(n);
        boolean[] visited = new boolean[n];
        Arrays.sort(nums);
        dfs(nums, n, ans, temp, visited);
        return ans;
    }

    private void dfs(int[] nums, int n, List<List<Integer>> ans,
                     List<Integer> temp, boolean[] visited) {
        if (temp.size() == n) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            temp.add(nums[i]);
            visited[i] = true;
            dfs(nums, n, ans, temp, visited);
            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }
}
