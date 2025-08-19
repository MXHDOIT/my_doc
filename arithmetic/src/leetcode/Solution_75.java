package leetcode;

/**
 * @author: maxinhang.
 * @version: 2025/8/19 21:36.
 */
public class Solution_75 {

    public static void main(String[] args) {
        new Solution_75().sortColors(new int[]{2, 0, 2, 1, 1, 0});
    }

    public void sortColors(int[] nums) {
        int n = nums.length;
        int redIndex = 0;
        int blueIndex = n - 1;
        int curIndex = 0;
        while (curIndex <= blueIndex) {
            int num = nums[curIndex];
            if (num == 0) {
                nums[curIndex] = nums[redIndex];
                nums[redIndex] = num;
                redIndex++;
                curIndex++;
            } else if (num == 2) {
                nums[curIndex] = nums[blueIndex];
                nums[blueIndex] = num;
                blueIndex--;
            } else {
                curIndex++;
            }
        }
    }
}
