package leetcode;

import java.util.Scanner;

/**
 * @author: maxinhang.
 */

/**
 * 题目描述
 * 小明正在玩一个游戏：这个游戏他需要从左往右依次经过n座山，其中第i座山的高度为hᵢ。在游戏开始之前，他需要装备足够高的登山鞋才行。
 *
 * 假设小明装备的登山鞋的耐久度为x，那么如果存在一个相邻两座山的高度差大于x，那么小明就无法攀爬下一座山。也就是说，任意相邻的两座山的高度之差不能超过x。
 *
 * 游戏正式开始之前，他利用前面关卡获得经验值来购买了k次操作，其中每一次操作都可以修改任意一座山的高度，并且可以将其修改为任意非负整数高度。他想知道：自己能够装备的登山鞋的耐久度最低可以是多少？
 *
 * 输入格式
 * 第一行一个正整数T，表示数据组数。
 *
 * 对于每一组数据：
 *
 * 第一行输入两个正整数n和k，分别表示山的数量和小明购买的操作次数
 *
 * 第二行输入n个整数h₁, h₂, ..., hₙ，表示山的高度
 *
 * 输出格式
 * 对于每一组数据，输出一行一个整数，表示小明经过若干次操作之后可以装备的登山鞋的最低耐久度。
 *
 * 数据范围
 * 1 ≤ k ≤ n ≤ 500
 *
 * 1 ≤ T ≤ 50
 *
 * 0 ≤ hᵢ ≤ 2×10⁹
 *
 * 时间限制
 * C/C++语言：2000MS
 *
 * 其他语言：4000MS
 *
 * 内存限制
 * C/C++语言：262144KB
 *
 * 其他语言：786432KB
 *
 * 样例输入
 * text
 * 3
 * 1 1
 * 2
 * 5 1
 * 1 2 4 7 8
 * 5 3
 * 6 4 7 1 0 5
 * 样例输出
 * text
 * 0
 * 2
 * 1
 * 问题本质
 * 在最多进行k次操作（每次操作可以将任意山的高度修改为任意非负整数）的情况下，求相邻山峰高度差的最大值的最小可能值。
 */
public class Solution_xiaomi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupSize = scanner.nextInt();
        for (int i = 0; i < groupSize; i++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }

            int solution = solution(n, k, arr);
            System.out.println(solution);
        }
    }

    // 选择最大差值，调整的位置可能有两个Index，1就是当前Index，2是前一个Index
    // 那么调整哪一个是一个难点???
    // 调整为多少是另外一个难点
        // 调整为前后两个值的平均值
    public static int solution(int n, int k, int[] arr) {
        int[] diff = new int[n];
        int maxDiff = 0;
        for (int i = 1; i < n; i++) {
            diff[i] = Math.abs(arr[i] - arr[i - 1]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }
        if (maxDiff == 0) {
            return 0;
        }

        for (int i = 0; i < k; i++) {
            int maxIndex = -1;
            maxDiff = 0;
            for (int j = 1; j < n; j++) {
                if (diff[j] > maxDiff) {
                    maxDiff = diff[j];
                    maxIndex = j;
                }
            }
            if (maxDiff == 0) {
                return 0;
            }

            //index的选择case by case选择的，逻辑不对
            int index = maxIndex;
            if (maxIndex == n - 1) {
                index = n - 2;
            }
            arr[index] = (arr[index - 1] + arr[index + 1]) / 2;
            diff[index] = Math.abs(arr[index] - arr[index - 1]);
            diff[index + 1] = Math.abs(arr[index + 1] - arr[index]);
        }

        maxDiff = 0;
        for (int i = 1; i < n; i++) {
            maxDiff = Math.max(maxDiff, diff[i]);
        }
        return maxDiff;
    }
}
