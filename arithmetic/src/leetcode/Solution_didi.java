package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: maxinhang.
 */
public class Solution_didi {
    // Redis LRU Redis get set

    static class ThreadSafeLRUCache {

        private int size;
        private int cap;
        private Map<Integer, Node> nodeMap;
        private Node head;
        private Node tail;

        public ThreadSafeLRUCache(int cap) {
            this.cap = cap;
            this.nodeMap = new HashMap<>(cap);
            // 初始化双向哨兵节点
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }

        /**
         * 线程安全的get操作
         */
        public synchronized int get(int key) {
            if (!nodeMap.containsKey(key)) {
                return -1;
            }
            Node node = nodeMap.get(key);
            moveToTail(node);  // 移动到尾部（最近使用）
            return node.value;
        }

        /**
         * 将节点移到尾部（先删除再添加）
         */
        private void moveToTail(Node node) {
            removeNode(node);
            addToTail(node);
        }

        /**
         * 从链表中移除节点
         */
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        /**
         * 将节点添加到尾部（尾节点的前一个）
         */
        private void addToTail(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }


        /**
         * 线程安全的put操作
         */
        public synchronized void put(int key, int value) {
            if (nodeMap.containsKey(key)) {
                // 已存在，更新值并移到尾部
                Node node = nodeMap.get(key);
                node.value = value;
                moveToTail(node);
                return;
            }

            // 容量满，删除最久未使用节点（头节点的下一个）
            if (size == cap) {
                Node delNode = head.next;
                removeNode(delNode);
                nodeMap.remove(delNode.key);
                size--;
            }

            // 添加新节点到尾部
            Node newNode = new Node(key, value);
            nodeMap.put(key, newNode);
            addToTail(newNode);
            size++;
        }

    }

    static class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
