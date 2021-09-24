package com.algorithm.test;

/**
 * @Description: 单词搜索  https://leetcode-cn.com/problems/word-search/
 * @author: zyu
 * @date: 2021年08月19日 13:50
 */
public class Code_Exist {

    public static boolean exist2(char[][] board, String word) {
        if (board.length * board[0].length < word.length()) {
            return false;
        }

        int row = board.length;
        int col = board[0].length;
        int[][] cache = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean flag = check1(board, cache, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean check1(char[][] board, int[][] cache, int row, int col, String word, int count) {
        if (count == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (board[row][col] != word.charAt(count)) {
            return false;
        }
        if (cache[row][col] == 1) {
            return false;
        }
        cache[row][col] = 1;
        if (word.charAt(count) != board[row][col]) {
            return false;
        }

        if(check1(board, cache, row + 1, col, word, count + 1)){
            return true;
        }
        if(check1(board, cache, row, col + 1, word, count + 1)){
            return true;
        }
        if(check1(board, cache, row - 1, col, word, count + 1)){
            return true;
        }
        if(check1(board, cache, row, col - 1, word, count + 1)){
            return true;
        }
        cache[row][col] = 0;
        return false;
    }

    private static boolean check(char[][] board, int[][] cache, int row, int col, String word, int k) {
        if (board[row][col] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }
        cache[row][col] = 1;
        int[][] direct = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : direct) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                if (cache[newRow][newCol] != 1) {
                    boolean flag = check(board, cache, newRow, newCol, word, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        cache[row][col] = 0;
        return result;
    }

    public static boolean exist1(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

    public static void main(String[] args) {
//        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
//        char[][] board = new char[][]{{'a','b'}};
        char[][] board = new char[][]{{'E', 'D', 'C'}, {'F', 'A', 'B'}, {'G', 'H', 'I'}};
        String word = "ABCDEFGHI";
        System.out.println(exist2(board, word));
        System.out.println(exist1(board, word));
    }
}
