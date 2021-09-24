package com.algorithm.test;

/**
 * @Description: 二叉树中的最大路径和 https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * @author: zyu
 * @date: 2021年08月27日 11:10
 */
public class Code_MaxPathSum {
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

    static int max = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        process(root);
        return max;
    }

    private static int process(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int val = node.val;
        int leftMax = Math.max(process(node.left), 0);
        int rightMax = Math.max(process(node.right), 0);
        //计算当前节点为头结点时左右子树都包含的最大值
        max = Math.max(val + leftMax + rightMax, max);
        //范围node这颗树的左子树的最大值或者右子树的最大值
        return node.val + Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {

    }
}
