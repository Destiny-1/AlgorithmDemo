package com.algorithm.test;

/**
 * @Description: 最大子序和  https://leetcode-cn.com/problems/maximum-subarray/
 * @author: zyu
 * @date: 2021年08月10日 14:11
 */
public class Code_MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] add = new int[nums.length];
        int max = nums[0];
        int min = nums[0];
        add[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            add[i] = nums[i] + add[i - 1];
            max = Math.max(add[i], max);
            min = Math.min(add[i], min);
        }
        return max - min;
    }

    public static void main(String[] args) {

    }
}
