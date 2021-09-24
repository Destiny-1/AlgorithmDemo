package com.algorithm.test;

import java.util.Stack;

/**
 * 以每个点位起点 左右寻找大于等于它长度的最大边界
 *
 * @Description: 盛水最多的容器  https://leetcode-cn.com/problems/container-with-most-water/
 * @author: zyu
 * @date: 2021年07月27日 17:28
 */
public class Code_maxArea {


    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while (left != right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(area, maxArea);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[] ints = {4, 3, 2, 1, 4};
//        int[] ints = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(ints));
    }
}
