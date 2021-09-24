package com.algorithm.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Description: 相交链表 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 * @author: zyu
 * @date: 2021年09月02日 16:28
 */
public class Code_GetIntersectionNode {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;
        while(node1 != node2){
            node1 = node1 == null? headB : node1.next;
            node2 = node2 == null ? headA : node2.next;
        }
        return node1;
    }

    private static ListNode reverseNode(ListNode node) {
        ListNode pre = null;
        ListNode now = node;
        ListNode next;
        while (now != null) {
            next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        return pre;
    }


    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode node1 = headA;
        while (node1 != null) {
            set.add(node1);
            node1 = node1.next;
        }
        ListNode node2 = headB;
        while (node2 != null) {
            if (set.contains(node2)) {
                return node2;
            }
            node2 = node2.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(0);
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);

        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(0);
        ListNode node7 = new ListNode(1);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node5.next = node3;
//        node6.next = node7;
//        node7.next = node2;

        System.out.println(getIntersectionNode(node, node5).val);
        System.out.println(getIntersectionNode1(node, node5).val);
    }
}
