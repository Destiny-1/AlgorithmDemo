package com.algorithm.test;

import java.util.*;

/**
 * @Description: 子集 https://leetcode-cn.com/problems/subsets/
 * @author: zyu
 * @date: 2021年08月18日 15:57
 */
public class Code_Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length - i; j++) {
                list.add(nums[j + i]);
                result.add(new ArrayList<>(list));
            }
        }
        return result;
    }


    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        process(nums, result, 0, list);
        return result;
    }

    private static void process(int[] nums, List<List<Integer>> result, int index, List<Integer> list) {
        if (index == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[index]);
        process(nums, result, index + 1, list);
        list.remove(list.size() - 1);
        process(nums, result, index + 1, list);
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 3};
//        List<List<Integer>> subsets = subsets2(nums);
//        System.out.println(subsets);
        System.out.println(2 << 3);
    }
}
