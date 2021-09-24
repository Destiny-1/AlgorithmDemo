package com.algorithm.test;

/**
 * @Description: 是否是平衡二叉树
 * @author: zyu
 * @date: 2021年06月02日 15:36
 */
public class IsBST {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(1);

        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
//        node3.left = node4;
//        node3.left = node6;
//        node3.right = node7;

        Boolean isAVL = isBST(node5);
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
        public int max;
        public int min;
        public Boolean isBST;

        public Info(int _max, int _min, Boolean _isBST) {
            max = _max;
            min = _min;
            isBST = _isBST;
        }
    }

    /***
     * 是否是二叉搜索树
     *
     * 二叉搜索树的判断条件是 左树节点中的最大值 小于该节点的值 右树节点的最小值 大于该节点的值
     *
     * 判断条件有四 1：左子树是BST 2:右子树是BST 3:左子树的最大值 小于节点的值 4：右子树的最小值大于节点的值
     * @param x
     * @return
     */
    public static Boolean isBST(Node x) {
        if (x == null) {
            return true;
        }
        return process(x).isBST;
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.value;
        int min = x.value;
        if (leftInfo != null) {
            max = Math.max(x.value, leftInfo.max);
            min = Math.min(x.value, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(x.value, rightInfo.max);
            min = Math.min(x.value, rightInfo.min);
        }

        Boolean isBST = true;
        if (leftInfo != null ) {
            if(leftInfo.max > x.value || !leftInfo.isBST){
                isBST = false;
            }
        }

        if (rightInfo != null ) {
            if(rightInfo.min < x.value || !rightInfo.isBST){
                isBST = false;
            }
        }
        return new Info(max, min, isBST);
    }
}
