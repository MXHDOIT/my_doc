package leetcode;

import cn.hutool.core.lang.Pair;

/**
 * @author: maxinhang.
 * @version: 2025/10/25 21:48.
 */
public class Soultion_mayi_1 {

    public static void main(String[] args) {
        Pair<Integer, int[]> maxSub = new Soultion_mayi_1().maxSub(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(maxSub);
    }

    //包含负整数的数组中，找到最大连续子序列和以及子序列
    public Pair<Integer, int[]> maxSub(int[] arr) {
        int max = arr[0];
        int prevSum = arr[0];
        int sIndex = 0;
        int eIndex = 0;
        int index = 1;
        int n = arr.length;
        while (index < n) {
            if (prevSum < 0) {
                prevSum = 0;
                sIndex = index;
                eIndex = index;
            }
            prevSum += arr[index];
            if (prevSum > max) {
                max = prevSum;
                eIndex = index;
            }
            index++;
        }
        int[] ans = new int[eIndex - sIndex + 1];
        for (int i = sIndex; i <= eIndex; i++) {
            ans[i - sIndex] = arr[i];
        }

        return Pair.of(max, ans);
    }
}
