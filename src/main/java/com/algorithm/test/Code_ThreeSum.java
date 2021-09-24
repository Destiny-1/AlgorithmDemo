package com.algorithm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 三数之和 https://leetcode-cn.com/problems/3sum/
 * @author: zyu
 * @date: 2021年07月30日 13:53
 */
public class Code_ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        //第一个值从左往右
        for (int first = 0; first < n; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int target = -nums[first];
            int third = n - 1;
            for (int second = first + 1; second < n; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                //因为是倒序排列 大的在后面 所有当2说之和大于target的时候 third可以往后减  如果小于的话就表示没结果
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                if (second == third) {
                    continue;
                }
                if (nums[second] + nums[third] == target) {
                    ArrayList<Integer> res = new ArrayList<>();
                    res.add(nums[first]);
                    res.add(nums[second]);
                    res.add(nums[third]);
                    result.add(res);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
    }
}
