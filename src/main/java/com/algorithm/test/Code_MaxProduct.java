package com.algorithm.test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Description: 乘积最大子数组
 * @author: zyu
 * @date: 2021年09月01日 15:53
 */
public class Code_MaxProduct {

    public static int maxProduct(int[] nums) {
        int N = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int a = nums[i];
            for (int j = i + 1; j < N; j++) {
                max = Math.max(max, a);
                a *= nums[j];
            }
            max = Math.max(max, a);
        }
        return max;
    }

    public static int dp(int[] nums) {
        int N = nums.length;
        int[] max = new int[N];
        int[] min = new int[N];
        for (int i = 0; i < N; i++) {
            max[i] = nums[i];
            min[i] = nums[i];
        }
        for (int i = 1; i < N; i++) {
            //当nums[i] 和max[i -1]同符号的时候
            max[i] = Math.max(max[i - 1] * nums[i], Math.max(nums[i], min[i - 1] * nums[i]));
            min[i] = Math.min(min[i - 1] * nums[i], Math.min(nums[i], max[i - 1] * nums[i]));
        }
        int maxValue = max[0];
        for (int i = 1; i < N; i++) {
            maxValue = Math.max(maxValue, max[i]);
        }
        return maxValue;
    }

    public static int dp2(int[] nums) {
        int N = nums.length;
        int maxValue = Integer.MIN_VALUE;
        int max = 1;
        int min = 1;
        for (int i = 0; i < N; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(max * nums[i], nums[i]);
            min = Math.min(min * nums[i], nums[i]);
            maxValue = Math.max(maxValue, max);
        }
        return maxValue;
    }


    public static void main(String[] args) {
//        int[] arr = new int[]{-4, -3, -2};
//        System.out.println(maxProduct(arr));
//        System.out.println(dp(arr));
//        System.out.println(dp2(arr));

        String a = "aaa/";
        String b = "/aaa";
        System.out.println(a.charAt(a.length() -1));
        System.out.println(a.substring(0, a.length() - 1));
        System.out.println(b.charAt(0));
        System.out.println(b.substring(1));
    }
}
