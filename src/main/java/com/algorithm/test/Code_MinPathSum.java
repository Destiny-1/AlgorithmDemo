package com.algorithm.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 最小路径和  https://leetcode-cn.com/problems/minimum-path-sum/
 * @author: zyu
 * @date: 2021年08月13日 14:23
 */
public class Code_MinPathSum {

    public static int dp(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //表示从m,n位置出发到终点一共几种走法
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        //最右行
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = dp[i + 1][n - 1] + grid[i][n - 1];
        }

        //等于自己加上后一个
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] += dp[m - 1][i + 1] + grid[m - 1][i];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }


    public static int minPathSum(int[][] grid) {
        Map<int[], Integer> cacheMap = new HashMap<>();
        return process(grid, 0, 0, 0, cacheMap);
    }

    /***
     *
     * @param row 当前行数
     * @param col  当前列数
     * @return
     */
    private static int process(int[][] grid, int row, int col, int result, Map<int[], Integer> cacheMap) {
        int maxRow = grid.length - 1;
        int maxCol = grid[0].length - 1;
        int[] key = new int[]{row, col};
        if (maxRow == row && maxCol == col) {
            cacheMap.put(key, result + grid[row][col]);
            return result + grid[row][col];
        }
        if (col > maxCol || row > maxRow) {
            cacheMap.put(key, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }
        Integer result1 = cacheMap.getOrDefault(new int[]{row + 1, col}, process(grid, row + 1, col, result + grid[row][col], cacheMap));
        Integer result2 = cacheMap.getOrDefault(new int[]{row, col + 1}, process(grid, row, col + 1, result + grid[row][col], cacheMap));
        return Math.min(result1, result2);
    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        int[][] arr = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum(arr));
        System.out.println(dp(arr));
    }
}
