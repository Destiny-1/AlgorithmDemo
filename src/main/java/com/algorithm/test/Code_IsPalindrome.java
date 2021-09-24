package com.algorithm.test;

/**
 * @Description: 回文链表   https://leetcode-cn.com/problems/palindrome-linked-list/
 * @author: zyu
 * @date: 2021年09月09日 17:41
 */
public class Code_IsPalindrome {

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

    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow;
        ListNode tail = reverseNode(fast);
        slow = head;
        while (slow != null && tail != null) {
            if (slow.val != tail.val) {
                return false;
            }
            slow = slow.next;
            tail = tail.next;
        }
        return true;
    }

    public static ListNode reverseNode(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(1);

        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        System.out.println(isPalindrome(listNode));
    }
}
