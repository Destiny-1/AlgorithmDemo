package com.algorithm.test;

import java.util.Stack;

/**
 * @Description: 柱状图中最大的矩形 https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * @author: zyu
 * @date: 2021年08月20日 13:45
 */
public class Code_LargestRectangleArea {

    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            //找出比i小的左边界
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? heights.length : stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max((right[i] - left[i] - 1) * heights[i], maxArea);
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1};
//        int[] arr = new int[]{2, 1, 2};
//        int[] arr = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(arr));
    }
}
