package com.algorithm.test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 实现树状结构的先序 中序和后序遍历
 * @author: zyu
 * @date: 2021年06月02日 10:44
 */
public class TraverseTree {

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
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
//        pre(treeNode1);
//        in(treeNode1);
//        pos(treeNode1);
        floor(treeNode1);
    }

    /***
     * 按层遍历
     * @param treeNode1
     */
    private static void floor(TreeNode treeNode1) {
        if (treeNode1 == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(treeNode1);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.println(poll.value);
            if (poll.left != null) {
                queue.add(poll.left);
            }

            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
    }

    /***
     * 后序遍历
     * @param treeNode1
     */
    private static void pos(TreeNode treeNode1) {
        if (treeNode1 == null) {
            return;
        }
        pos(treeNode1.left);
        pos(treeNode1.right);
        System.out.println(treeNode1.value);
    }

    /***
     * 中序遍历
     * @param treeNode1
     */
    private static void in(TreeNode treeNode1) {
        if (treeNode1 == null) {
            return;
        }
        in(treeNode1.left);
        System.out.println(treeNode1.value);
        in(treeNode1.right);

    }

    /***
     * 二叉树的先序遍历
     * @param treeNode1
     */
    private static void pre(TreeNode treeNode1) {
        if (treeNode1 == null) {
            return;
        }
        System.out.println(treeNode1.value);
        pre(treeNode1.left);
        pre(treeNode1.right);
    }


//    //随机生成二叉树
//    public static TreeNode genBT() {
//        for (int i = 1; i < 1000; i++) {
//
//        }
//    }
}
