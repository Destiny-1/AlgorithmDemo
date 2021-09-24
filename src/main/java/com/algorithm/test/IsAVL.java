package com.algorithm.test;

/**
 * @Description: 是否是平衡二叉树
 * @author: zyu
 * @date: 2021年06月02日 15:36
 */
public class IsAVL {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(1);

        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
//        node3.left = node4;
//        node3.left = node6;
//        node3.right = node7;

        Boolean isAVL = isAVL(node1);
        System.out.println(isAVL);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int _value) {
            value = _value;
        }
    }

    public static class Info {
        public int height;
        public Boolean isAVL;

        public Info(int _height, Boolean _isAVL) {
            height = _height;
            isAVL = _isAVL;
        }
    }

    /***
     * 是否是平衡二叉树
     *
     * 平衡二叉树的判断条件 左边的高度和右边的高度之差不能超过一
     * @param x
     * @return
     */
    public static Boolean isAVL(Node x) {
        if (x == null) {
            return true;
        }
        return process(x).isAVL;
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, true);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        Boolean isAVL = true;
        if (!leftInfo.isAVL || !rightInfo.isAVL) {
            isAVL = false;
        }
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isAVL = false;
        }
        return new Info(height, isAVL);
    }
}
