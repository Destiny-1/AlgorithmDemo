package com.algorithm.test;

/**
 * @Description: 是否是满二叉树
 * @author: zyu
 * @date: 2021年06月02日 15:36
 */
public class IsFull {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(1);

//        node1.left = node2;
        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
//        node3.left = node6;
//        node3.right = node7;

        Boolean full = isFull(node1);
        System.out.println(full);
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
        public int size;

        public int height;

        public Info(int _size, int _height) {
            size = _size;
            height = _height;
        }
    }

    /***
     * 是否是满二叉树
     *
     * 满二叉树的判断条件  1 << height - 1 = size   height为高度 size为节点数
     * @param x
     * @return
     */
    public static Boolean isFull(Node x) {
        if (x == null) {
            return true;
        }
        Info info = process(x);
        return (1 << info.height == (info.size + 1));
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int size = leftInfo.size + rightInfo.size + 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new Info(size, height);
    }
}
