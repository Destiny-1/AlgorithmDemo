package com.algorithm.test;

/**
 * @Description: 环形链表 II https://leetcode-cn.com/problems/linked-list-cycle-ii/
 * @author: zyu
 * @date: 2021年08月30日 15:27
 */
public class Code_DetectCycle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                if(fast == head){
                    return head;
                }
                fast = head;
                while (fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3 = new ListNode(-4);
        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(2);
        ListNode listNode6 = new ListNode(2);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode1;

        listNode4.next = listNode5;
        listNode5.next = listNode4;

        System.out.println(detectCycle(listNode4).val);
        System.out.println(detectCycle(listNode).val);
        System.out.println(detectCycle(listNode6));
    }
}
