package com.algorithm.test;

import java.util.Stack;

/**
 * @Description: 已知一棵搜索二叉树上没有重复值的节点，
 * 现在有一个数组arr，是这棵搜索二叉树先序遍历的结果
 * 请根据arr生成整棵树并返回头节点
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * @author: zyu
 * @date: 2021年09月14日 14:06
 */
public class Code_BSTCombine {

    public static class Node{
       private int val;
       private Node left;
       private Node right;

       public Node (int val){
           this.val = val;
           this.left = null;
           this.right = null;
       }
    }

    public static Node bstCombine1(int [] arr){
        int N = arr.length;
        int[] nearBig = new int[N];
        for(int i = 0; i< N;i++){
            nearBig[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        int index = 0;
        while (index < N){
            while (!stack.isEmpty() && arr[stack.peek()] < arr[index]){
                nearBig[stack.pop()] = index;
            }
            stack.push(index++);
        }
        return process1(0,N-1,arr,nearBig);
    }

    private static Node process1(int L, int R, int[] arr, int[] nearBig) {
        if(L > R){
            return null;
        }
        int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R +1 : nearBig[L];
        Node node = new Node(arr[L]);
        node.left = process1(L +1,firstBig -1,arr,nearBig);
        node.right = process1(firstBig, R,arr,nearBig);
        return node;
    }

    public static Node bstCombine(int [] arr){
        int N = arr.length;
        return process(0,N-1,arr);
    }

    private static Node process(int L, int R, int[] arr) {
        if(L > R){
            return null;
        }
        int val = arr[L];
        int firstBig = L +1;
        Node node = new Node(val);
        for(;firstBig <= R;firstBig++){
            if(arr[firstBig] > val){
                break;
            }
        }
        node.left = process(L +1,firstBig -1,arr);
        node.right = process(firstBig, R,arr);
        return node;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{5,3,1,4,7,6,8};
        Node node = bstCombine(arr);
        Node node1 = bstCombine1(arr);
        System.out.println(node.val);
    }
}
