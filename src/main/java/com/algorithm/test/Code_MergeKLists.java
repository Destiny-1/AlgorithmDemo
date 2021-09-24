package com.algorithm.test;

/**
 * @Description: 合并k个有序链表
 * @author: zyu
 * @date: 2021年08月03日 16:00
 */
public class Code_MergeKLists {


    //左边链表的最右侧节点
    private static ListNode endNode = null;

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

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        }
        int pos = 0;
        for (int i = 0; i < lists.length; i++) {
            ListNode list = lists[i];
            while (list != null) {
                endNode = list;
                list = list.next;
            }
            pos++;
            break;
        }
        if (endNode == null) {
            return null;
        }
        ListNode ans = merge(lists, lists[0], pos);
        printNode(ans);
        return ans;
    }

    /***
     * 合并lists数组
     * @param lists
     * @param left
     */
    private static ListNode merge(ListNode[] lists, ListNode left, int pos) {
        for (int i = pos; i < lists.length; i++) {
            ListNode right = lists[i];
            if (right != null) {
                //左边链表和右边合并
                left = combineNode(left, right);
            }
        }
        return left;
    }

    private static void printNode(ListNode re) {
        while (re != null) {
            System.out.println(re.val);
            re = re.next;
        }
    }

    private static ListNode combineNode(ListNode left, ListNode right) {
        ListNode result = new ListNode(1);
        ListNode point = result;
        while (left != null && right != null) {

            //右边的最小值大于左边的最大值
            if (right.val >= endNode.val) {
                while (left != null) {
                    point.next = left;
                    left = left.next;
                    point = point.next;
                }
                break;
            }
            if (left.val >= right.val) {
                point.next = right;
                right = right.next;
            } else {
                point.next = left;
                left = left.next;
            }
            point = point.next;
        }

        while (left != null) {
            point.next = left;
            break;
        }

        while (right != null) {
            point.next = right;
            break;
        }
        while (point.next != null) {
            point = point.next;
        }
        endNode = point;
        return result.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(4);
        ListNode node12 = new ListNode(5);

        ListNode node2 = new ListNode(1);
        ListNode node21 = new ListNode(3);
        ListNode node22 = new ListNode(4);

        ListNode node3 = new ListNode(2);
        ListNode node31 = new ListNode(6);

        node1.next = node11;
        node11.next = node12;

        node2.next = node21;
        node21.next = node22;

        node3.next = node31;
        ListNode[] arr = new ListNode[]{node1, node2, node3};


//        ListNode listNode1 = combineNode(node1, node2);
//        ListNode listNode = combineNode(listNode1, node3);
        ListNode listNode = mergeKLists(arr);
    }

}
