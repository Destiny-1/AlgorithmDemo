package com.algorithm.test;

/**
 * @Description: 随机生成二叉树
 * @author: zyu
 * @date: 2021年06月03日 14:19
 */
public class GenRandomTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int _val) {
            value = _val;
        }
    }

    public static void main(String[] args) {
        int level = 1;
        int maxLevel = 5;
        int maxValue = 100;
        for(int i = 0; i < 1000; i ++){
            Node node = genRandomTree(level, maxLevel, maxValue);
            if(node != null){
                System.out.println(node);
            }
        }

    }

    /***
     * 生成随机树
     * @param level
     * @param maxLevel
     * @param maxValue
     */
    private static Node genRandomTree(int level, int maxLevel, int maxValue) {
        //用来制造随机空节点的
        if(level > maxLevel || Math.random() < 0.5){
            return null;
        }

        Node node = new Node((int)(Math.random() * 100));
        node.left = genRandomTree(level + 1, maxLevel, maxValue);
        node.right = genRandomTree(level + 1, maxLevel, maxValue);
        return node;
    }
}
