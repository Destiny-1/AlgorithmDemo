package com.algorithm.test;

/**
 * @Description: 路径总和  https://leetcode-cn.com/problems/path-sum/
 * @author: zyu
 * @date: 2021年08月12日 10:20
 */
public class Code_HasPathSum {

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

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return process(root, targetSum);
    }

    public static boolean process(TreeNode tree, int targetSum) {
        int val = tree.val;
        if (val == targetSum) {
            if (tree.left == null && tree.right == null) {
                return true;
            }
        }
        targetSum = targetSum - val;
        boolean result = false;
        if (tree.left != null) {
            result |= process(tree.left, targetSum);
        }
        if (tree.right != null) {
            result |= process(tree.right, targetSum);
        }
        return result;
    }

}
