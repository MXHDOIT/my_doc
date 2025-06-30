package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: maxinhang.
 */
public class Solution_56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(o -> o[0]));
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; i++) {
            if (i == 0) {
                merged.add(intervals[i]);
            } else {
                int[] prevInterval = merged.get(merged.size() - 1);
                int[] interval = intervals[i];
                if (interval[0] > prevInterval[1]) {
                    merged.add(interval);
                } else {
                    prevInterval[1] = Math.max(prevInterval[1], interval[1]);
                }
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
