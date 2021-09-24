package com.algorithm.test;

/**
 * @Description: 除自身以外数组的乘积  https://leetcode-cn.com/problems/product-of-array-except-self/
 * @author: zyu
 * @date: 2021年09月11日 16:27
 */
public class Code_ProductExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        //顺着
        int[] arr = new int[N];
        //倒着
        int[] arr2 = new int[N];
        int[] ret = new int[N];
        arr[0] = 1;
        for (int i = 1; i < N; i++) {
            arr[i] = arr[i - 1] * nums[i - 1];
        }
        arr2[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            arr2[i] = arr2[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < N; i++) {
            ret[i] = arr[i] * arr2[i];
        }
        return ret;
    }


    public static int[] productExceptSelf2(int[] nums) {
        int N = nums.length;
        int[] ret = new int[N];
        ret[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            ret[i] = ret[i + 1] * nums[i + 1];
        }
        int multiBefore = 1;
        for (int i = 0; i < N; i++) {
            ret[i] = ret[i] * multiBefore;
            multiBefore *= nums[i];
        }
        return ret;
    }

    public static int[] correct(int[] arr) {
        int N = arr.length;
        int[] ret = new int[N];
        int max = 1;
        for (int i = 0; i < N; i++) {
            max *= arr[i];
        }

        for (int i = 0; i < N; i++) {
            ret[i] = max / arr[i];
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] ret = productExceptSelf2(arr);
        int[] ret1 = productExceptSelf(arr);
        int[] cor = correct(arr);
        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
            System.out.println(ret1[i]);
            System.out.println(cor[i]);
        }
    }
}
