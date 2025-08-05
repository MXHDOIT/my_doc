package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: maxinhang.
 */
public class Solution_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 单调队列 及时去掉无用数据，保证双端队列有序
        int n = nums.length;
        Deque<Integer> queue = new LinkedList<>();
        int[] result = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            //1. 入
            while (!queue.isEmpty() && nums[queue.getLast()] <= num) {
                queue.removeLast();
            }
            queue.addLast(i);
            //2. 出
            int left = i - k + 1; // 窗口左端点
            if (queue.getFirst() < left) {
                queue.removeFirst();
            }
            //3. 记录答案
            if (left >= 0) {
                result[left] = nums[queue.getFirst()];
            }
        }
        return result;
    }
}
