package com.algorithm.test;

/**
 * @Description: 不同路径  https://leetcode-cn.com/problems/unique-paths/
 * @author: zyu
 * @date: 2021年08月13日 13:43
 */
public class Code_UniquePaths {

    public static int uniquePaths(int m, int n) {
        return process(m - 1, n - 1, 0, 0);
    }

    /***
     *
     * @param maxRow  最大行数
     * @param maxCol 最大列数
     * @param row 当前行数
     * @param col  当前列数
     * @return
     */
    private static int process(int maxRow, int maxCol, int row, int col) {
        if (maxRow == row && maxCol == col) {
            return 1;
        }
        if (col > maxCol || row > maxRow) {
            return 0;
        }
        return process(maxRow, maxCol, row + 1, col) + process(maxRow, maxCol, row, col + 1);
    }


    public static int dp(int m, int n) {
        //表示从m,n位置出发到终点一共几种走法
        int[][] dp = new int[m][n];

        //最右行
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }

        for (int j = 0; j < n; j++) {
            dp[m - 1][j] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int M = (int) (Math.random() * 20) + 1;
            int N = (int) (Math.random() * 20) + 1;
            int res1 = uniquePaths(M, N);
            int res2 = dp(M, N);
            if (res1 != res2) {
                System.out.println("M:" + M + ",N:" + N);
                System.out.println("res1:" + res1 + ",res2:" + res2);
            }
        }
    }
}
