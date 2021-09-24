package com.algorithm.test;

/**
 * @Description: 验证二叉搜索树 https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @author: zyu
 * @date: 2021年08月23日 10:51
 */
public class Code_IsValidBST {

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

    public static class Info {
        int max;
        int min;
        boolean isBST;

        Info() {
        }

        Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process2(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private static boolean process2(TreeNode node, long max, long min) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return process2(node.left, node.val, min) && process2(node.right, max, node.val);
    }


    public static boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int val = node.val;
        int max = val;
        int min = val;
        boolean isBST = true;
        if (leftInfo != null) {
            if (val <= leftInfo.max || !leftInfo.isBST) {
                isBST = false;
            }
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            if (val >= rightInfo.min || !rightInfo.isBST) {
                isBST = false;
            }
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        return new Info(max, min, isBST);
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(6);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(7);

        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;

        //[5,4,6,null,null,3,7]
        System.out.println(isValidBST1(treeNode));
        System.out.println(isValidBST(treeNode));
    }
}
