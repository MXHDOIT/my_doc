package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_lcr_170 {
    public static void main(String[] args) {
        new Solution_lcr_170().reversePairs(new int[]{9, 7, 5, 4, 6});
    }

    public int reversePairsOpt(int[] record) {
        int n = record.length;
        return mergeSort(record, new int[n], 0, n - 1);
    }

    private int mergeSort(int[] record, int[] temp, int l, int r) {
        if (l >= r) return 0;
        int mid = (l + r) / 2;
        int res = mergeSort(record, temp, l, mid) + mergeSort(record, temp, mid + 1, r);
        int leftIndex = l;
        int rightIndex = mid + 1;
        int index = leftIndex;
        for (int i = l; i <= r; i++) {
            temp[i] = record[i];
        }
        while (leftIndex <= mid && rightIndex <= r) {
            if (temp[leftIndex] <= temp[rightIndex]) {
                record[index++] = temp[leftIndex++];
            } else {
                record[index++] = temp[rightIndex++];
                res += mid - leftIndex + 1;
            }
        }

        while (leftIndex <= mid) {
            record[index++] = temp[leftIndex++];
        }
        while (rightIndex <= r) {
            record[index++] = temp[rightIndex++];
        }
        return res;
    }

    public int reversePairs(int[] record) {
        int n = record.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = record[i];
            for (int j = 0; j < i; j++) {
                if (record[j] > cur) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
