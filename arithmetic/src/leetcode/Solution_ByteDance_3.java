package leetcode;

import java.util.BitSet;

/**
 * @author: maxinhang.
 */
public class Solution_ByteDance_3 {

    public static void main(String[] args) {
        int numSize = uniqueNumSize(new int[]{1, 1, 3, 4});
        System.out.println(numSize);
    }
    //输入：3亿个有重复的正整数数组
    //内存限制：不超过 1GB
    //输出：只出现一次的数字个数

    public static int uniqueNumSize(int[] arr) {
        BitSet bitSet1 = new BitSet();
        BitSet bitSet2 = new BitSet();

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (bitSet1.get(num)) {
                bitSet2.set(num);
            } else {
                bitSet1.set(num);
            }
        }

        bitSet1.xor(bitSet2);
        return bitSet1.cardinality();
    }
}
