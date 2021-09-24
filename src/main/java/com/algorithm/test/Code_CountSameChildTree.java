package com.algorithm.test;

/**
 * @Description: 如果一个节点X，它左树结构和右树结构完全一样
 * 那么我们说以X为头的树是相等树
 * 给定一棵二叉树的头节点head
 * 返回head整棵树上有多少棵相等子树
 * @author: zyu
 * @date: 2021年09月14日 16:42
 */
public class Code_CountSameChildTree {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static int CountSameChildTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return CountSameChildTree(root.left) + CountSameChildTree(root.right) + (same(root.left, root.right) ? 1 : 0);
    }


    private static boolean same(TreeNode node1, TreeNode node2) {
        if (node1 == null ^ node2 == null) {
            return false;
        }
        if (node1 == null && node2 == null) {
            return true;
        }
        return (node1.val == node2.val) && same(node1.left, node2.left) && same(node1.right, node2.right);
    }

    public static class Info {
        private int ans;
        private String str;

        public Info(int ans, String str) {
            this.ans = ans;
            this.str = str;
        }
    }

    /***
     * 方法二 用hash字符串判断左右子树是否相同 让比较时间从O(N) 到了O(1)
      * @param head
     * @return
     */
    public static int sameNumber2(TreeNode head) {
        String algorithm = "SHA-256";
        Hash hash = new Hash(algorithm);
        return process(head, hash).ans;
    }


    private static Info process(TreeNode head, Hash hash) {
        if (head == null) {
            return new Info(0, hash.hashCode("#,"));
        }

        Info leftInfo = process(head.left, hash);
        Info rightInfo = process(head.right, hash);
        int ans = leftInfo.ans + rightInfo.ans + (leftInfo.str.equals(rightInfo.str) ? 1 : 0);
        String str = head.val + "," + leftInfo.str + rightInfo.str;
        return new Info(ans, hash.hashCode(str));
    }

    public static TreeNode randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        TreeNode head = Math.random() < 0.2 ? null : new TreeNode((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            TreeNode head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = CountSameChildTree(head);
            int ans2 = sameNumber2(head);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
            }

        }
        System.out.println("测试结束");

    }

}
