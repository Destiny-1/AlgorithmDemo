package com.algorithm.test;

/**
 * @Description: 删除单链表的第n个节点
 * @author: zyu
 * @date: 2021年07月30日 17:02
 */
public class Code_RemoveNthFromEnd {
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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode root = reverseNode(head);
        int count = 1;
        ListNode pre = null;
        ListNode ans = root;
        while (ans != null) {
            if (count == n) {
                //删除的是第一个节点
                if (pre == null) {
                    root = root.next;
                } else {
                    pre.next = ans.next;
                }
                break;
            }
            count++;
            pre = ans;
            ans = ans.next;
        }
        ListNode listNode = reverseNode(root);
        return listNode;
    }

    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        int count = 1;
        ListNode fast = head;
        ListNode slow = head;
        while (count <= n && fast != null) {
            fast = fast.next;
            count++;
        }
        //一开始就跳到了结束 则删除节点就是首节点
        if (fast == null) {
            head = head.next;
        } else {
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            slow.next = slow.next.next;
        }
        return head;
    }

    private static ListNode reverseNode(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode listNode = removeNthFromEnd1(node1, 2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}
