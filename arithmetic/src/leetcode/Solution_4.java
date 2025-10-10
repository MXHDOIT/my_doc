package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_4 {

    public static void main(String[] args) {
        Solution_4 solution = new Solution_4();
        int[] nums1 = {1, 4};
        int[] nums2 = {2, 3, 5, 6};
        System.out.println(solution.findMedianSortedArraysOpt(nums1, nums2));
//
//        int[] nums3 = {1, 2};
//        int[] nums4 = {3, 4};
//        System.out.println(solution.findMedianSortedArraysOpt(nums3, nums4));
    }


    public double findMedianSortedArraysOpt(int[] nums1, int[] nums2) {
        int num1Length = nums1.length;
        int num2Length = nums2.length;
        int length = num1Length + num2Length;
        if (length % 2 == 1) {
            int k = (length + 1) / 2;
            return findKOpt(nums1, 0, num1Length - 1, nums2, 0, num2Length - 1, k) * 1.0;
        } else {
            int k = length / 2;
            int j = k + 1;
            return (findKOpt(nums1, 0, num1Length - 1, nums2, 0, num2Length - 1, k)
                    + findKOpt(nums1, 0, num1Length - 1, nums2, 0, num2Length - 1, j)) / 2.0;
        }
    }

    private int findKOpt(int[] num1, int s1, int e1, int[] num2, int s2, int e2, int k) {
        int len1 = e1 - s1 + 1;
        int len2 = e2 - s2 + 1;
        if (len1 > len2) {
            return findKOpt(num2, s2, e2, num1, s1, e1, k);
        }
        if (len1 == 0) {
            return num2[s2 + k - 1];
        }
        if (k == 1) {
            return Math.min(num1[s1], num2[s2]);
        }

        // 计算两个数组中要比较的位置（取k/2或数组剩余长度的较小值）
        int i = s1 + Math.min(len1, k / 2) - 1;  // 注意-1，因为数组下标从0开始
        int j = s2 + Math.min(len2, k / 2) - 1;

        if (num1[i] < num2[j]) {
            return findKOpt(num1, i + 1, e1, num2, s2, e2, k - (i - s1 + 1));
        } else {
            return findKOpt(num1, s1, e1, num2, j + 1, e2, k - (j - s2 + 1));
        }
    }

//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int n = nums1.length;
//        int m = nums2.length;
//        int k = (n + m + 1) / 2;
//        int j = (n + m + 2) / 2;
//        return (findK(nums1, 0, n - 1, nums2, 0, m - 1, k)
//                + findK(nums1, 0, n - 1, nums2, 0, m - 1, j)) / 2.0;
//    }
//
//    public int findK(int[] nums1, int sIndex1, int eIndex1,
//                     int[] nums2, int sIndex2, int eIndex2,
//                     int k) {
//        int len1 = eIndex1 - sIndex1 + 1;
//        int len2 = eIndex2 - sIndex1 + 1;
//        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
//        if (len1 > len2) return findK(nums2, sIndex2, eIndex2, nums1, sIndex1, eIndex1, k);
//        if (len1 == 0) return nums2[sIndex2 + k - 1];
//
//        int i = sIndex1 + Math.min(len1, k / 2) - 1;
//        int j = sIndex2 + Math.min(len2, k / 2) - 1;
//        if (nums1[i] > nums2[j]) {
//            return findK(nums1, sIndex1, sIndex2, nums2, j + 1, eIndex2, k - (j - sIndex2 + 1));
//        } else {
//            return findK(nums1, i + 1, eIndex1, nums2, sIndex2, eIndex2, k - (i - sIndex1 + 1));
//        }
//    }
}
