package leetcode;

import java.util.Random;

/**
 * @author: maxinhang.
 */
public class Solution_912 {
    public int[] sortArray(int[] arr) {
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    public void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        //随机
        int i = new Random().nextInt(r - l + 1) + l;
        swap(arr, l, i);
        int num = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= num) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
            }
            while (left < right && arr[left] <= num) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }
        }
        arr[left] = num;
        sort(arr, l, left - 1);
        sort(arr, left + 1, r);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
