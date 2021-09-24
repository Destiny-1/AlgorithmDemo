package com.algorithm.test;

/**
 * @Description: 相机最小覆盖问题 https://leetcode.com/problems/binary-tree-cameras/
 * @author: zyu
 * @date: 2021年09月15日 16:11
 */
public class Code_BinaryTreeCameras {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static enum Status {
        //没有被覆盖，被覆盖但是节点上无相机 , 被覆盖且节点有相机
        UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA
    }

    public static class Info {
        private Status status;
        private int cameras;

        public Info(Status status, int cameras) {
            this.status = status;
            this.cameras = cameras;
        }
    }

    public static int minCameraCover(TreeNode head) {
        Info info = process(head);
        return info.cameras + (info.status == Status.UNCOVERED ? 1 : 0);
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return new Info(Status.COVERED_NO_CAMERA, 0);
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int cameras = leftInfo.cameras + rightInfo.cameras;
        //子节点至少有一个没有被覆盖
        if (leftInfo.status == Status.UNCOVERED || rightInfo.status == Status.UNCOVERED) {
            return new Info(Status.COVERED_HAS_CAMERA, cameras + 1);
        }

        //如果子节点至少有一个包含相机
        if (leftInfo.status == Status.COVERED_HAS_CAMERA || rightInfo.status == Status.COVERED_HAS_CAMERA) {
            return new Info(Status.COVERED_NO_CAMERA, cameras);
        }

        return new Info(Status.UNCOVERED, cameras);
    }
}
