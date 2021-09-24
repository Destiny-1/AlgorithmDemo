package com.algorithm.test;

import java.util.HashSet;

/**
 * @Description: 只出现一次的数字 https://leetcode-cn.com/problems/single-number/
 * @author: zyu
 * @date: 2021年08月27日 16:16
 */
public class Code_SingleNumber {
    public static int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        return set.iterator().next();
    }

    public static int singleNumber1(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret = ret ^ nums[i];
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 2, 1, 2};
        System.out.println(singleNumber1(arr));
    }
}
