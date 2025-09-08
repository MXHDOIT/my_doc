package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: maxinhang.
 */
public class LFUCache {
    private int idx = 0;
    Map<Integer, Node> nodeMap;
    Queue<Node> queue;
    int capacity;
    int size;

    public LFUCache(int capacity) {
        this.nodeMap = new HashMap<>(capacity);
        this.queue = new PriorityQueue<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) {
            return -1;
        }
        queue.remove(node);
        node.frequency++;
        node.time = idx++;
        queue.add(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = nodeMap.get(key);
        if (node != null) {
            node.value = value;
            queue.remove(node);
            node.frequency++;
            node.time = idx++;
            queue.add(node);
        } else {
            if (size == capacity) {
                Node minNode = queue.remove();
                nodeMap.remove(minNode.key);
                size--;
            }
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            newNode.frequency = 1;
            newNode.time = idx++;
            nodeMap.put(key,newNode);
            queue.offer(newNode);
            size++;
        }
    }

    private static class Node implements Comparable<Node> {
        int key;
        int value;
        int frequency;
        int time;


        @Override
        public int compareTo(Node o) {
            int diff = this.frequency - o.frequency;
            return diff == 0 ? this.time - o.time : diff;
        }
    }
}
