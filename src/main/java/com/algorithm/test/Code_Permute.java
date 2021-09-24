package com.algorithm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 全排列  https://leetcode-cn.com/problems/permutations/
 * @author: zyu
 * @date: 2021年08月09日 14:03
 */
public class Code_Permute {
    public static List<List<Integer>> result = new ArrayList<>();

//    public List<List<Integer>> permute(int[] nums) {
//        List<Integer> list = new ArrayList<>();
//        int[] visited = new int[nums.length];
//        process(nums, list, visited);
//        return result;
//    }
//
//    public void process(int nums[], List<Integer> list, int[] visited) {
//        int N = nums.length;
//        if (N == list.size()) {
//            result.add(new ArrayList<>(list));
//            return;
//        }
//
//        for (int i = 0; i < N; i++) {
//            if (visited[i] == 1) {
//                continue;
//            }
//            list.add(nums[i]);
//            visited[i] = 1;
//            process(nums, list, visited);
//            //收集的结果回退
//            visited[i] = 0;
//            list.remove(list.size() - 1);
//        }
//    }


    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<Integer> base = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            base.add(nums[i]);
        }
        process(base, list);
        return result;
    }

    public void process(List<Integer> base, List<Integer> list) {
        int N = base.size();
        if (N == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = N - 1; i >= 0; i--) {
            list.add(base.get(i));
            ArrayList<Integer> integers = new ArrayList<>(base);
            integers.remove(i);
            process(integers, list);
            list.remove(list.size() - 1);
        }
    }

    public int[] remove(int[] nums, int index) {
        int[] res = new int[nums.length - 1];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i != index) {
                res[count++] = nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1};
        int[] nums = new int[]{1, 2, 3};
//        int[] nums = new int[]{1, 2, 3};
        Code_Permute code_permute = new Code_Permute();
        code_permute.permute(nums);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
