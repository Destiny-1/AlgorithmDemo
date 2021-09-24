package com.algorithm.test;

/**
 * @Description: 搜索二维矩阵 II
 * @author: zyu
 * @date: 2021年09月13日 21:10
 */
public class Code_SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        int N = matrix[0].length;
        int row = M -1;
        int col = 0;
        while(row >=0 && col < N){
            if(matrix[row][col] > target){
                row--;
            }else if(matrix[row][col] < target){
                col++;
            }else{
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int[] firstCol = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            firstCol[i] = matrix[i][0];
        }
    }
}
