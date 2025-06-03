package sort;

import sort.impl.MergeSort;

/**
 * @author: maxinhang.
 */
public class SortTest {
    public static void main(String[] args) {
        int[] arr = {1,10,2,34,213,62,21};
        Sort sort = new MergeSort();
        sort.sort(arr);
        System.out.println(arr);
    }
}
