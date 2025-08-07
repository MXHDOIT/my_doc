package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_189 {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int step = k % n;
        reversal(nums, 0, n - 1);
        reversal(nums, 0, step - 1);
        reversal(nums, step, n - 1);
    }

    public void reversal(int[] nums, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            int num = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = num;
            startIndex++;
            endIndex--;
        }
    }

    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        int step = k % n;
        while (step-- > 0) {
            int num = nums[n - 1];
            for (int i = n - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = num;
        }
    }
}
