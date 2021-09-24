package com.algorithm.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 跳跃游戏  https://leetcode-cn.com/problems/jump-game/
 * @author: zyu
 * @date: 2021年08月11日 14:25
 */
public class Code_CanJump {

    public static boolean canJump(int[] nums) {
        Map<Integer, Boolean> cacheMap = new HashMap<>(24);
        return process(nums, 0, cacheMap);
    }

    public static boolean canJump1(int[] nums) {
        int N = nums.length;
        int right = 0;
        for (int i = 0; i < N; i++) {
            if (i <= right) {
                right = Math.max(right, i + nums[i]);
            }
            if (right >= N - 1) {
                return true;
            }
        }
        return false;
    }

    //从下标位置开始跳
    public static Boolean process(int[] nums, int index, Map<Integer, Boolean> cacheMap) {
        int Len = nums.length;
        if (index == nums.length - 1) {
            return true;
        }
        if (nums[index] == 0) {
            return false;
        }

        Boolean flag = false;
        int N = nums[index];
        if (N + index >= Len - 1) {
            return true;
        }
        for (int i = 1; i <= N; i++) {
            if (index + i < Len) {
                flag |= cacheMap.containsKey(index + i) ? cacheMap.get(index + i) : process(nums, index + i, cacheMap);
            }
        }
        cacheMap.put(index, flag);
        return flag;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5};
//        int[] arr = new int[]{3,2,1,0,4};
        System.out.println(canJump(arr));
        System.out.println(canJump1(arr));
    }
}
