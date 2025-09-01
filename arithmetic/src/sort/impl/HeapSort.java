package sort.impl;

import sort.Sort;

/**
 * @author: maxinhang.
 */
public class HeapSort implements Sort {
    @Override
    public void sort(int[] arr) {
        //1. 建大顶堆
        buildHead(arr);
        //2. 堆顶和列表尾部交互
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            //3. 调整堆
            shiftDown(arr, i, 0);
        }
    }

    private void buildHead(int[] arr) {
        int n = arr.length;
        int lastParent = (n - 2) / 2;
        for (int i = lastParent; i >= 0; i--) {
            shiftDown(arr, n, i);
        }
    }

    private void shiftDown(int[] arr, int length, int index) {
        int left = index * 2 + 1;
        if (left < length) {
            int maxValueIndex = index;
            if (arr[left] > arr[maxValueIndex]) {
                maxValueIndex = left;
            }
            int right = left + 1;
            if (right < length) {
                if (arr[right] > arr[maxValueIndex]) {
                    maxValueIndex = right;
                }
            }
            if (maxValueIndex != index) {
                swap(arr, maxValueIndex, index);
                shiftDown(arr, length, maxValueIndex);
            }
        }
    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
