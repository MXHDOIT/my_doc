package leetcode;

/**
 * @author: maxinhang.
 */
public class Solution_4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int k = (n + m + 1) / 2;
        int j = (n + m + 2) / 2;
        return (findK(nums1, 0, n - 1, nums2, 0, m - 1, k)
                + findK(nums1, 0, n - 1, nums2, 0, m - 1, j)) / 2.0;
    }

    public int findK(int[] nums1, int sIndex1, int eIndex1,
                     int[] nums2, int sIndex2, int eIndex2,
                     int k) {
        int len1 = eIndex1 - sIndex1 + 1;
        int len2 = eIndex2 - sIndex1 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return findK(nums2, sIndex2, eIndex2, nums1, sIndex1, eIndex1, k);
        if (len1 == 0) return nums2[sIndex2 + k - 1];

        int i = sIndex1 + Math.min(len1, k / 2) - 1;
        int j = sIndex2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] > nums2[j]) {
            return findK(nums1, sIndex1, sIndex2, nums2, j + 1, eIndex2, k - (j - sIndex2 + 1));
        } else {
            return findK(nums1, i + 1, eIndex1, nums2, sIndex2, eIndex2, k - (i - sIndex1 + 1));
        }
    }
}
