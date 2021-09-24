package com.algorithm.test;

import java.util.*;

/**
 * @Description: 最长连续序列 https://leetcode-cn.com/problems/longest-consecutive-sequence/
 * @author: zyu
 * @date: 2021年08月27日 14:48
 */
public class Code_LongestConsecutive {

    public static int longestConsecutive(int[] nums) {
        int max = 0;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            hashSet.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!hashSet.contains(nums[i] - 1)) {
                int num = nums[i];
                int count = 1;
                while (hashSet.contains(num + 1)) {
                    count++;
                    num++;
                }
                max = Math.max(count, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-7, -1, 3, -9, -4, 7, -3, 2, 4, 9, 4, -9, 8, -7, 5, -1, -7};
        System.out.println(longestConsecutive(arr));
    }
}
