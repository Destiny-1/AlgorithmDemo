package com.algorithm.test;

/**
 * @Description: 编辑距离 https://leetcode-cn.com/problems/edit-distance/
 * @author: zyu
 * @date: 2021年08月16日 16:25
 */
public class Code_MinDistance {

    public static int minDistance(String word1, String word2) {
        int left = word1.length();
        int right = word2.length();

        if (left * right == 0) {
            return left + right;
        }

        return process(word1, word2, 0, 0);
    }

    /***
     * 从word1的left位置 word2的right位置开始对比有几种情况
     * @param word1
     * @param word2
     * @return
     */
    private static int process(String word1, String word2, int left, int right) {
        //左边没值了 所以结果是加上右边剩下的值 已经走了right了剩余word2.length -right
        if (left == word1.length()) {
            return word2.length() - right;
            //右边已经匹配完了 把左边多余的删掉
        } else if (right == word2.length()) {
            return word1.length() - left;
        } else if (word1.charAt(left) == word2.charAt(right)) {
            return process(word1, word2, left + 1, right + 1);
        } else {
            //左边字符当前这一位删除或右边添加(左边后移一位，右边和下一个继续匹配)
            int result1 = process(word1, word2, left + 1, right);
            int result2 = process(word1, word2, left, right + 1);
            //左边变化
            int result3 = process(word1, word2, left + 1, right + 1);
            return Math.min(result1, Math.min(result2, result3)) + 1;
        }
    }


    public static int dp(String word1, String word2) {
        int M = word1.length();
        int N = word2.length();
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= N; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[M][N];
    }


    public static void main(String[] args) {
        String str1 = "horse";
//        String str1 = "a";
        String str2 = "ros";
//        String str2 = "b";
        System.out.println(dp(str1, str2));
        System.out.println(minDistance(str1, str2));
    }
}
