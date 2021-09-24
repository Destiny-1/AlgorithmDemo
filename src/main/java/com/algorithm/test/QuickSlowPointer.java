package com.algorithm.test;

/**
 * @Description: 快慢指针
 * @author: zyu
 * @date: 2021年06月01日 15:26
 * <p>
 * 快慢指针是在链表问题中常用的一种措施，主要是用来在遍历一遍的情况下就寻找到一个链表的中点
 * <p>
 * 主要是分为两个指针 一个快指针一个慢指针 快指针一次走两步 慢指针一次走一步 当快指针走的终点的时候 慢指针所在的位置就是中点
 */
public class QuickSlowPointer {

    static class Node {
        public int value;

        public Node next;

        public Node() {
        }

        public Node(int _value) {
            this.value = _value;
        }

        public Node(int _value, Node _node) {
            this.value = _value;
            this.next = _node;
        }

    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
//        Node node6 = new Node(6);
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node3;
//        Node node = midNode(node1);
        Node meetNode = isCircle(node1);
        System.out.println(meetNode == null ? null : meetNode.value);
//        System.out.println(node.value);
    }

    /***
     * 返回中点  当节点总数为奇数时返回中点 节点为偶数时 返回节点的前节点
     * 如 123456 中点返回3节点
     * @param root
     * @return
     */
    public static Node midNode(Node root) {
        if (root == null || root.next == null) {
            return root;
        }
        Node fast = root;
        Node slow = root;
        while (fast != null && fast.next != null) {
            //当fast.next.next为空 表示的是这是偶数个节点
            if (fast.next.next == null) {
                //slow = slow.next; //加上这一句 返回的是后中点 不加返回的是前中点
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /***
     * 判断此单链表是否有环 有环的话 返回入环节点 无环的话返回null
     * @return
     */
    public static Node isCircle(Node root) {
        if (root == null) {
            return null;
        }

        Node fast = root;
        Node slow = root;
        Boolean isMeet = false;
        while (fast.next != null && fast.next.next != null && !isMeet) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                isMeet = true;
            }
        }
        //不是环  返回null
        if(!isMeet){
            return null;
        }
        fast = root;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
