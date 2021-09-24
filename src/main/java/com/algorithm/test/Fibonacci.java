package com.algorithm.test;

/**
 * @Description: 斐波那契数列解决办法
 * @author: zyu
 * @date: 2021年06月11日 10:09
 */
public class Fibonacci {

    /**
     * 利用暴力递归的方法解决
     * @param N
     * @return
     */
    public static int f(int N) {
        if (N == 1) {
            return 1;
        }

        if (N == 2) {
            return 1;
        }

        return f(N - 1) + f(N - 2);
    }

    /***
     * 利用动态规划的方法解决
     * @param N
     * @return
     */
    public static int f2(int N) {
        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 1;
        return process1(N, dp);
    }

    private static int process1(int N, int[] dp) {
        if (dp[N] > 0) {
            return dp[N];
        } else {
            int ans = process1(N - 1, dp) + process1(N -2 , dp);
            dp[N] = ans;
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(f(40));
        System.out.println(System.currentTimeMillis());
        System.out.println(f2(40));
        System.out.println(System.currentTimeMillis());
    }
}
