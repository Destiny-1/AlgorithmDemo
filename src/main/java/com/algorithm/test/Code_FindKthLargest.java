package com.algorithm.test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @Description: 数组中的第K个最大元素 https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @author: zyu
 * @date: 2021年09月07日 15:37
 */
public class Code_FindKthLargest {

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int less = nums.length - k + 1;
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() == less) {
                if (queue.peek() > nums[i]) {
                    queue.poll();
                    queue.add(nums[i]);
                }
            } else {
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1, 5, 6, 4};
        int k = 6;
        System.out.println(findKthLargest(arr, k));
    }
}
