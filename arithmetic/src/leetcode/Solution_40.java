package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: maxinhang.
 * @version: 2025/9/10 10:23.
 */
public class Solution_40 {

    public static void main(String[] args) {
        new Solution_40().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = candidates.length;
        List<Integer> temp = new ArrayList<>(n);
        Arrays.sort(candidates);
        dfs(candidates, n, target, 0, temp, ans);
        return ans;
    }

    private void dfs(int[] candidates, int n, int target, int index,
                     List<Integer> temp, List<List<Integer>> ans) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < n; i++) {
            if (i > index && candidates[i] == candidates[i - 1])
                continue;
            temp.add(candidates[i]);
            dfs(candidates, n, target - candidates[i], i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}
