package com.algorithm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 排序链表 https://leetcode-cn.com/problems/sort-list/
 * @author: zyu
 * @date: 2021年08月31日 17:21
 */
public class Code_SortList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode sortList(ListNode head) {
        List<Integer> arrList = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            arrList.add(node.val);
            node = node.next;
        }
        mergeSort(0, arrList.size() - 1, arrList);
        ListNode node1 = new ListNode();
        ListNode node2 = node1;
        for (int i = 0; i < arrList.size(); i++) {
            node2.next = new ListNode(i);
            node2 = node2.next;
        }
        return node1.next;
    }

    private static void mergeSort(int left, int right, List<Integer> arrList) {
        if (left == right) {
            return;
        }
        int mid = left - (left - right) / 2;
    }


    private static ListNode getMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(4);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode node4 = sortList(node);
        while (node4 != null) {
            System.out.println(node4.val);
            node4 = node4.next;
        }
    }
}
