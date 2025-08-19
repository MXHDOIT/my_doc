package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: maxinhang.
 * @version: 2025/8/18 22:57.
 */
public class Solution_679 {

    private static final double EPS = 1e-9;

    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int card : cards) {
            list.add((double) card);
        }
        return dfs(list);
    }

    public boolean dfs(List<Double> list) {
        int n = list.size();
        if (n == 1) {
            return Math.abs(list.get(0) - 24) < EPS;
        }
        for (int i = 0; i < n; i++) {
            Double x = list.get(i);
            for (int j = i + 1; j < n; j++) {
                Double y = list.get(j);
                List<Double> candidates = new ArrayList<>();
                candidates.add(x-y);
                candidates.add(y-x);
                candidates.add(x+y);
                candidates.add(x*y);
                if (Math.abs(y) >= EPS) {
                    candidates.add(x/y);
                }
                if (Math.abs(x) >= EPS) {
                    candidates.add(y/x);
                }
                List<Double> newList = new ArrayList<>(list);
                newList.remove(j);
                for (Double candidate : candidates) {
                    newList.set(i,candidate);
                    if (dfs(newList)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
