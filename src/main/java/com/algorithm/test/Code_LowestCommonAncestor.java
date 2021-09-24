package com.algorithm.test;

/**
 * @Description: 二叉树的最近公共祖先  https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * @author: zyu
 * @date: 2021年09月10日 16:42
 */
public class Code_LowestCommonAncestor {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Info {
        private boolean isContainP;
        private boolean isContainQ;
        private TreeNode ret;

        public Info() {
            this.isContainP = false;
            this.isContainQ = false;
            ret = null;
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Info info = process(root, p, q);
        return info.ret;
    }

    public static Info process(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return new Info();
        }
        Info info = new Info();

        if (node.val == p.val) {
            info.isContainP = true;
        } else if (node.val == q.val) {
            info.isContainQ = true;
        }
        Info leftInfo = process(node.left, p, q);
        Info rightInfo = process(node.right, p, q);
        if (leftInfo.ret != null) {
            return leftInfo;
        }
        if (rightInfo.ret != null) {
            return rightInfo;
        }

        if (leftInfo.isContainP || rightInfo.isContainP) {
            info.isContainP = true;
        }

        if (leftInfo.isContainQ || rightInfo.isContainQ) {
            info.isContainQ = true;
        }

        if (info.isContainQ && info.isContainP) {
            info.ret = node;
        }
        return info;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(2);
        TreeNode treeNode5 = new TreeNode(7);
        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(0);
        TreeNode treeNode8 = new TreeNode(8);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode4.left = treeNode5;
        treeNode4.right = treeNode6;
        treeNode2.left = treeNode7;
        treeNode2.right = treeNode8;

        System.out.println(lowestCommonAncestor(treeNode2, treeNode2,treeNode7).val);
    }
}
