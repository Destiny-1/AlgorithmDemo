package com.algorithm.test;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @Description: 判断是否是全完二叉树的类
 * @author: zyu
 * @date: 2021年06月02日 11:18
 */
public class IsCBT {

    public static class TreeNode {
        public int value;

        public TreeNode left;

        public TreeNode right;

        public TreeNode(int _value) {
            value = _value;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
//        treeNode2.left = treeNode4;
//        treeNode3.right = treeNode5;

        Boolean cbt = isCBT(treeNode1);
        System.out.println(cbt);
    }

    /***
     *  节点x想要是完全二叉树的满足条件
     *  1:左子树是满二叉树 右子树也是满二叉树 且高度相等
     *  2：左子树是完全二叉树 右子树是满二叉树 且 左子树的高度等于右子树高度 + 1
     *  3：左子树是满二叉树 右子树也是满二叉树 且左子树高度等于右子树高度 + 1
     *  4：左子树满二叉树 右子树完全二叉树 且左子树高度和右子树高度相同
     *
     *
     * @param treeNode1
     */
    private static Boolean isCBT(TreeNode treeNode1) {
        if (treeNode1 == null) {
            return true;
        }
        return process(treeNode1).isCBT;
    }


    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(true, true, 0);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        Boolean isFull = leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height);
        Boolean isCBT = false;
        if (leftInfo.isCBT && rightInfo.isFull && (leftInfo.height == rightInfo.height + 1)) {
            isCBT = true;
        }
        if (leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height + 1)) {
            isCBT = true;
            isFull = true;
        }
        if (leftInfo.isFull && rightInfo.isCBT && (leftInfo.height == rightInfo.height)) {
            isCBT = true;
        }
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new Info(isFull, isCBT, height);
    }

    public static class Info {
        //是否是满二叉树
        public Boolean isFull;
        //是否是完全二叉树
        public Boolean isCBT;
        public int height;

        public Info(Boolean full, Boolean cbt, int hei) {
            isFull = full;
            isCBT = cbt;
            height = hei;
        }
    }
}
