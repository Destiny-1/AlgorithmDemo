package com.algorithm.test;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 正则匹配字符串  https://leetcode-cn.com/problems/regular-expression-matching/
 * @author: zyu
 * @date: 2021年07月29日 15:33
 */
public class Code_IsMatch {

    /***
     *
     * @param s 表示匹配字符串
     * @param p 表示包含的字符串
     * @return
     */
    public static boolean isMatch(String s, String p) {
        Map<Pair<String, String>, Boolean> cacheMap = new HashMap<>();
        return process(s, p, cacheMap);
    }

    /***
     * 匹配规则分为两种 第一种是单个匹配  单个匹配的情况分为两种  1： s[0] = p[0] 2: p[0] = '.'
     * 多个匹配也分成两种情况  1： s[0] != p[0] p[1] = '*' 此时s[0] 和p[0][1]抵消
     * 2：s[0] = p[0] p[1] = '*' 此时又可以分两种情况  1： s[0] 和p[0][1]抵消 2： s[1]继续和p[0][1] 匹配
     * @param s
     * @param p
     * @return
     */
    private static Boolean process(String s, String p, Map<Pair<String, String>, Boolean> cacheMap) {
        Pair<String, String> pair = new Pair<>(s, p);
        if (cacheMap.containsKey(pair)) {
            return cacheMap.get(pair);
        }
        //base case 当p为空的时候判断s为不为空 s不为空的话 就返回false 为空的话就返回true
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        //单个匹配情况
        Boolean oneMatch = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') ? true : false;
        Boolean twoMatch = false;
        if (p.length() > 1 && p.charAt(1) == '*') {
            twoMatch = true;
        }
        if (twoMatch) {
            //*表示匹配多个前提是单个匹配正确
            cacheMap.put(pair, process(s, p.substring(2), cacheMap) || (oneMatch && process(s.substring(1), p, cacheMap)));
        } else {
            cacheMap.put(pair, oneMatch && process(s.substring(1), p.substring(1), cacheMap));
        }
        return cacheMap.get(pair);
    }

    /***
     * 当前的取值依赖于之前的取值
     * @param s
     * @param p
     * @return
     */
    public static boolean dp(String s, String p) {
        int M = s.length();
        int N = p.length();
        //默认都是false
        boolean dp[][] = new boolean[M + 1][N + 1];
        //base case 当p为空的时候判断s为不为空 s不为空的话 就返回false 为空的话就返回true
        dp[0][0] = true;

        //s为空 p不为空的时 当p[i -1]为*的时候 dp[0][i] = dp[0][i -2]
        for (int j = 1; j <= N; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                //多个匹配情况
                if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                    //单个匹配情况
                } else {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[M][N];
    }

    public boolean isMatch1(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        //S和P都为空时，匹配
        dp[0][0] = true;
        //当S为空，P不空，要看P是否为 a*b*这种结构
        for(int j = 1; j <= m; ++j) {
            if(p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                //当前字符串是*，可以将 字符+*全部忽略掉，取f[i][j-2]的值
                //或者看模式串P的上一个字符串是否能跟字符串S匹配
                //如果能匹配上，可以忽略掉模式串的 字符+*，也可以忽略掉字符串S中的当前字符
                //这里其实跟递归一样，也有两条转移路径
                if(p.charAt(j - 1) == '*') {
                    if(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    }
                    else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
                //单个字符匹配的情况
                else {
                    if(p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {

        String s = "aa";
        String p = "a*";
//        String s = "mississippi";
//        String p = "mis*is*p*.";
        System.out.println(dp(s, p));
        System.out.println(dp(s, p));
    }
}
