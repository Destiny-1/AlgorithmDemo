package com.algorithm.test;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 二叉树展开为链表 https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * @author: zyu
 * @date: 2021年08月26日 16:09
 */
public class Code_Flatten {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static List<TreeNode> list = new ArrayList<>();

    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root);
        TreeNode node = root;
        for (int i = 1; i < list.size(); i++) {
            node.right = list.get(i);
            node.left = null;
            node = node.right;
        }
    }

    public static void process(TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node);
        process(node.left);
        process(node.right);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(6);
//        treeNode.left = treeNode1;
//        treeNode1.left = treeNode2;
//        treeNode1.right = treeNode3;
//        treeNode.right = treeNode4;
//        treeNode4.right = treeNode5;
        flatten(treeNode);
        System.out.println(111);
    }
}
