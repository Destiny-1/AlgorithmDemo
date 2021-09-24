package com.algorithm.test;

/**
 * @Description: 编辑距离问题  字符串s1 通过增加修改删除操作变成s2的最小代价
 * @author: zyu
 * @date: 2021年09月14日 18:09
 */
public class Code_EditCost {

    /****
     * 将s1 变成s2
     * @param s1  操作字符
     * @param s2  目标字符
     * @param r  将一个字符替换为另外一个字符代价为r
     * @param a  新增一个字符代价为a
     * @param d  删除一个字符代价为r
     * @return
     */
    public static int editCost(String s1, String s2, int r, int a, int d) {
        int M = s1.length();
        int N = s2.length();
        //dp[i][j] 表示字符串s1的0-i 变成 s2 0-j的最好代价
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 0; i <= M; i++) {
            //将长度
            dp[i][0] = i * d;
        }

        for (int i = 0; i <= N; i++) {
            dp[0][i] = i * a;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                //先把s1的前i-1 个字符和s2的钱j-1个字符调整好把s1的第i个字符改为s2的第j个字符
                dp[i][j] = dp[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : r);
                //先把s1的前i-1 个字符和s2的钱j个字符调整好 然后把s1的第i个字符删除
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + d);
                //先把s1的前i个字符和s2的前j-1个字符调整好 然后在s1后面加上一个
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + a);
            }
        }
        return dp[M][N];
    }

    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(editCost(str1, str2, 1, 1, 1));
    }
}
