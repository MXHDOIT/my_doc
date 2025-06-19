package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permute(nums, visited, new ArrayList<>(), result);
        return result;
    }

    public void permute(int[] nums, boolean[] visited, List<Integer> temp, List<List<Integer>> result) {
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            temp.add(nums[i]);
            if (nums.length == temp.size()) {
                result.add(new ArrayList<>(temp));
            } else {
                visited[i] = true;
                permute(nums, visited, temp, result);
                visited[i] = false;
            }
            temp.remove(temp.size() - 1);

        }
    }
}
