package com.algorithm.test;

/**
 * @Description: TODO
 * @author: zyu
 * @date: 2021年07月26日 17:37
 */
public class TwoNumberSum {
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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode result = new ListNode(0);
        ListNode cur = result;
        int count = 0;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + count;
            if (val < 10) {
                cur.next = new ListNode(val);
                count = 0;
            } else {
                cur.next = new ListNode(val - 10);
                count = 1;
            }
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int val = l1.val + count;
            if (val < 10) {
                cur.next = new ListNode(val);
                count = 0;
            } else {
                count = 1;
                cur.next = new ListNode(val - 10);
            }
            cur = cur.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int val = l2.val + count;
            if (val < 10) {
                cur.next = new ListNode(val);
                count = 0;
            } else {
                count = 1;
                cur.next = new ListNode(val - 10);
            }
            cur = cur.next;
            l2 = l2.next;
        }

        if (count > 0) {
            cur.next = new ListNode(count);
        }

        result = result.next;
        return result;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);
        ListNode listNode7 = new ListNode(9);
        ListNode listNode8 = new ListNode(9);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(9);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;


        listNode8.next = listNode9;
        listNode9.next = listNode10;
        ListNode listNode = addTwoNumbers(listNode1, listNode4);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
