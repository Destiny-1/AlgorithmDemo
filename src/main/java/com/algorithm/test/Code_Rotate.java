package com.algorithm.test;

/**
 * (x,y)旋转九十度之后的逻辑是(y, N- x) N为下标的最大值
 *
 * @Description: 旋转图像 https://leetcode-cn.com/problems/rotate-image/
 * @author: zyu
 * @date: 2021年08月09日 15:33
 */
public class Code_Rotate {
    public void rotate(int[][] matrix) {
        int M =  matrix.length - 1;
        for (int i = 0; i < (M + 1) / 2; i++) {
            for (int j = 0; j < (M + 2) / 2; j++) {
                // 当前位置  ----- 旋转之后的位置  N = M +1
                //matrix[i][j] ---> matrix[j][N - i -1]
                // matrix[j][N- i -1] ---> matrix[N- i- 1][N- j -1]
                //marix[N - i -1][N- j -1] ---> matrix[N-j-1][i]
                //matrix[N-j-1][i] --- > matrix[i][j]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[M - j][i];
                matrix[M - j][i] = matrix[M - i][M - j];
                matrix[M - i][M - j] = matrix[j][M - i];
                matrix[j][M - i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] n = new int[4][4];
        n[0] = new int[]{5, 1, 9, 11};
        n[1] = new int[]{2, 4, 8, 10};
        n[2] = new int[]{13, 3, 6, 7};
        n[3] = new int[]{15, 14, 12, 16};
        Code_Rotate code_rotate = new Code_Rotate();
        code_rotate.rotate(n);
        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n[i].length; j++) {
                System.out.println(n[i][j]);
            }
        }
    }
}
