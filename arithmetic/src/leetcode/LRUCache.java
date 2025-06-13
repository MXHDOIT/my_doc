package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap + 双向链表
 * @author: maxinhang.
 */
class LRUCache {

    class DataListNode {
        int key;
        int value;
        DataListNode prev;
        DataListNode next;

        public DataListNode() {
        }

        public DataListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DataListNode> cache = new HashMap<>();
    private int capacity;
    private int size;
    private DataListNode head;
    private DataListNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new DataListNode();
        this.tail = new DataListNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DataListNode dataNode = cache.get(key);
        if (dataNode == null) {
            return -1;
        }
        moveToHead(dataNode);
        return dataNode.value;
    }

    public void put(int key, int value) {
        DataListNode dataListNode = cache.get(key);
        if (dataListNode == null) {
            dataListNode = new DataListNode(key, value);
            cache.put(key, dataListNode);
            addToHead(dataListNode);
            size++;
            if (size > capacity) {
                DataListNode tailNode = removeTail();
                cache.remove(tailNode.key);
                size--;
            }
        } else {
            dataListNode.value = value;
            moveToHead(dataListNode);
        }
    }

    private DataListNode removeTail() {
        DataListNode node = tail.prev;
        remove(node);
        return node;
    }

    private void remove(DataListNode dataListNode) {
        dataListNode.prev.next = dataListNode.next;
        dataListNode.next.prev = dataListNode.prev;
    }

    private void addToHead(DataListNode dataListNode) {
        dataListNode.prev = head;
        dataListNode.next = head.next;
        head.next.prev = dataListNode;
        head.next = dataListNode;
    }

    private void moveToHead(DataListNode dataListNode) {
        remove(dataListNode);
        addToHead(dataListNode);
    }

}