package leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: maxinhang.
 */
public class MedianFinder {

    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    Queue<Integer> minHeap = new PriorityQueue<>();

    /**
     * 左边大顶堆，右边小顶堆，小的加左边，大的加右边，平衡两堆数，新加就弹出，堆顶给堆加，奇数就取多，偶数取除2
     */
    public MedianFinder() {

    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        int minSize = minHeap.size();
        int maxSize = maxHeap.size();
        if(minSize == maxSize) return ( minHeap.peek() + maxHeap.peek() ) / 2.0;
        return (minSize > maxSize) ? minHeap.peek() : maxHeap.peek();
    }
}
