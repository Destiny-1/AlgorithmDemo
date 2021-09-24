package com.algorithm.test;

/**
 * @Description: 最大正方形 https://leetcode-cn.com/problems/maximal-square/
 * @author: zyu
 * @date: 2021年09月08日 11:27
 */
public class Code_MaximalSquare {
    public static int maximalSquare(char[][] matrix) {
        int[][] arr = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                arr[0][i] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    arr[i][j] = arr[i - 1][j] + 1;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                int num = arr[i][j];
                if (num > 0 && max <= 0) {
                    max = 1;
                }
                int count = 1;
                int width = num;
                while ((j + count) < arr[0].length && count < num) {
                    width = Math.min(width, arr[i][j + count]);
                    max = Math.max(Math.min(width * width, (count + 1) * (count + 1)), max);
                    count++;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'0', '0', '1', '1', '1'}};
        char[][] matrix2 = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalSquare(matrix));
        System.out.println(maximalSquare(matrix2));
    }
}
