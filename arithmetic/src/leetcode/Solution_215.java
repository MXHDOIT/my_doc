package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: maxinhang.
 */
public class Solution_215 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i < k) {
                priorityQueue.add(num);
            } else {
                Integer peekNum = priorityQueue.peek();
                if (peekNum < num) {
                    priorityQueue.poll();
                    priorityQueue.add(num);
                }
            }
        }
        return priorityQueue.peek();
    }
}
