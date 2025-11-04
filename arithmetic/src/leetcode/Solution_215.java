package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: maxinhang.
 */
public class Solution_215 {

    public static void main(String[] args) {
        int kthLargest1 = new Solution_215().findKthLargest1(new int[]{3, 2, 1, 5, 6, 4}, 1);
        System.out.println(kthLargest1);
    }

    public int findKthLargest1(int[] nums, int k) {
        return findKthQuickSort(nums, 0, nums.length - 1, k);
    }

    private int findKthQuickSort(int[] nums, int left, int right, int k) {
        int l = left, r = right;
        int num = nums[left];
        while (left < right) {
            while (left < right && nums[right] < num) {
                right--;
            }
            if (left < right) {
                nums[left] = nums[right];
                left++;
            }
            while (left < right && nums[left] > num) {
                left++;
            }
            if (left < right) {
                nums[right] = nums[left];
                right--;
            }
        }
        if (left == k - 1) {
            return num;
        } else if (left < k - 1) {
            return findKthQuickSort(nums, left + 1, r, k);
        } else {
            return findKthQuickSort(nums, l, left - 1, k);
        }
    }

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
