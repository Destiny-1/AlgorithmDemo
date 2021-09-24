package com.algorithm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 对称二叉树 https://leetcode-cn.com/problems/symmetric-tree/
 * @author: zyu
 * @date: 2021年08月23日 16:37
 */
public class Code_IsSymmetric {

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

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        List<Integer> preList = new ArrayList<>();
        List<Integer> posList = new ArrayList<>();
        process(root, preList, posList);
        int N = preList.size();
        for (int i = 0; i < preList.size(); i++) {
            if (!preList.get(i).equals(posList.get(N - i - 1))) {
                return false;
            }
        }
        return true;
    }

    private static void process(TreeNode node, List<Integer> preList, List<Integer> posList) {
        if (node == null) {
            preList.add(-99);
            posList.add(-99);
            return;
        }

        preList.add(node.val);
        process(node.left, preList, posList);
        process(node.right, preList, posList);
        posList.add(node.val);
    }

    public static boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return false;
        }
       return process1(root, root);
    }

    private static boolean process1(TreeNode root, TreeNode root1) {
        if(root == null && root1 == null){
            return true;
        }
        if(root1 == null || root == null){
            return false;
        }

        return root.val == root1.val && process1(root.left, root1.right) && process1(root.right, root1.left);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(3);

        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;

        System.out.println(isSymmetric(treeNode));
        System.out.println(isSymmetric1(treeNode));
    }
}
