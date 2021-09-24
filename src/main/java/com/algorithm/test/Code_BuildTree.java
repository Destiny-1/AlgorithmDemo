package com.algorithm.test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Description: 从前序与中序遍历序列构造二叉树  https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @author: zyu
 * @date: 2021年08月25日 14:11
 */
public class Code_BuildTree {

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

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 1 && inorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        int inIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int nowVal = preorder[i];
            TreeNode preNode = stack.peek();
            if (preNode.val != inorder[inIndex]) {
                preNode.left = new TreeNode(nowVal);
                stack.push(preNode.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inIndex]) {
                    preNode = stack.pop();
                    inIndex++;
                }
                preNode.right = new TreeNode(nowVal);
                stack.push(preNode.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
    }
}
