package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: maxinhang.
 */
public class Solution_207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> visiting = new HashSet<>();

        Map<Integer, Set<Integer>> num2Rely = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int curValue = prerequisite[0];
            int relyValue = prerequisite[1];
            num2Rely.computeIfAbsent(curValue, k -> new HashSet<>()).add(relyValue);
        }

        for (Integer value : num2Rely.keySet()) {
            if (visit(visited,visiting,num2Rely,value)) {
                continue;
            }
            return false;
        }
        return true;
    }

    private boolean visit(Set<Integer> visited, Set<Integer> visiting,
                          Map<Integer, Set<Integer>> num2Rely, Integer value) {
        if (visited.contains(value)) {
            return true;
        }
        if (visiting.contains(value)) {
            return false;
        }
        if (num2Rely.containsKey(value)) {
            Set<Integer> relyValues = num2Rely.get(value);
            visiting.add(value);
            for (Integer relyValue : relyValues) {
                if (visit(visited,visiting,num2Rely,relyValue)) {
                    continue;
                }
                return false;
            }
            visiting.remove(value);
            visited.add(value);
        } else {
            visited.add(value);
        }
        return true;
    }
}
