package sort.impl;

import sort.Sort;

/**
 * 归并排序
 *  时间复杂度 O(n log n)
 *  空间复杂度 O(n)
 *  稳定性 稳定
 * @author: maxinhang.
 */
public class MergeSort implements Sort {
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    //[sIndex,eIndex]
    private void mergeSort(int[] arr, int sIndex, int eIndex) {
        if (eIndex <= sIndex) {
            return;
        }
        int midIndex = (sIndex + eIndex) / 2;
        mergeSort(arr, sIndex, midIndex);
        mergeSort(arr, midIndex + 1, eIndex);
        merge(arr, sIndex, midIndex, eIndex);
    }

    private void merge(int[] arr, int sIndex, int midIndex, int eIndex) {
        int sIndex1 = sIndex;
        int sIndex2 = midIndex + 1;
        int[] temp = new int[eIndex - sIndex + 1];
        int index = 0;
        while (sIndex1 <= midIndex && sIndex2 <= eIndex) {
            if (arr[sIndex1] <= arr[sIndex2]) {
                temp[index++] = arr[sIndex1++];
            } else {
                temp[index++] = arr[sIndex2++];
            }
        }
        while (sIndex1 <= midIndex) {
            temp[index++] = arr[sIndex1++];
        }
        while (sIndex2 <= eIndex) {
            temp[index++] = arr[sIndex2++];
        }
        for (int i = sIndex; i <= eIndex; i++) {
            arr[i] = temp[i - sIndex];
        }
    }
}
