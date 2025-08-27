package leetcode;

import java.util.Random;

/**
 * @author: maxinhang.
 */
public class Solution_470 {
    public int rand10() {
        int num; // 存储扩展后的随机数（0-48）
        do {
            // 步骤1：生成 [0,48] 的均匀随机数
            num = (rand7() - 1) * 7 + (rand7() - 1);
        } while (num >= 40); // 步骤2：拒绝 >=40 的值（仅保留 0-39）
        // 步骤3：将 [0,39] 映射到 [0,9]，再偏移到 [1,10]
        return num % 10 + 1;
    }

    public int rand7() {
        return new Random().nextInt(7);
    }
}
