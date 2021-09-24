package com.algorithm.test;

import java.util.*;

/**
 * @Description: LRU 缓存机制 https://leetcode-cn.com/problems/lru-cache/
 * @author: zyu
 * @date: 2021年08月30日 16:51
 */
public class Code_LRUCache {
    static class LRUCache {
        static class LinkNode {
            private int key;
            private int value;
            private LinkNode next;
            private LinkNode prev;

            public LinkNode() {
            }

            public LinkNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        Map<Integer, LinkNode> cacheMap = new HashMap<>();
        int size;
        public int capacity;
        public LinkNode tail, head;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            tail = new LinkNode();
            head = new LinkNode();
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }

        public int get(int key) {
            if (cacheMap.containsKey(key)) {
                LinkNode node = cacheMap.get(key);
                // 先删除节点 然后 节点移到尾节点
                moveToTail(node);
                return node.value;
            } else {
                return -1;
            }
        }

        private void moveToTail(LinkNode node) {
            removeNode(node);
            addTailNode(node);
        }

        private void addTailNode(LinkNode node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }

        private void removeNode(LinkNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public void put(int key, int value) {
            LinkNode node = cacheMap.get(key);
            if (node == null) {
                LinkNode linkNode = new LinkNode(key, value);
                size++;
                addTailNode(linkNode);
                cacheMap.put(key, linkNode);
                if (size > capacity) {
                    LinkNode lNode = removeHead();
                    cacheMap.remove(lNode.key);
                    --size;
                }
            } else {
                node.value = value;
                moveToTail(node);
            }
        }

        private LinkNode removeHead() {
            LinkNode node = head.next;
            head.next = node.next;
            node.next.prev = head;
            return node;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
