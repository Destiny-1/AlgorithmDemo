package com.algorithm.test;

import java.util.HashMap;

/**
 * @Description: String str, int K, String[] parts, int[] record
 * str一定要分割成k个部分，分割出来的每部分在parts里必须得有
 * 那一部分的得分在record里
 * 请问，str切成k个部分，最大得分是多少？
 * @author: zyu
 * @date: 2021年09月16日 19:15
 */
public class Code_SplitStringMaxValue {

    public static int maxRecord1(String str, int K, String[] parts, int[] record) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        HashMap<String, Integer> cacheMap = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            cacheMap.put(parts[i], record[i]);
        }
        return process(0, str, K, cacheMap);
    }

    private static int process(int L, String str, int rest, HashMap<String, Integer> cacheMap) {
        if (rest < 0) {
            return -1;
        }
        if (L == str.length()) {
            return rest == 0 ? 0 : -1;
        }
        int score = -1;
        for (int end = L; end < str.length(); end++) {
            String s = str.substring(L, end + 1);
            if (cacheMap.containsKey(s)) {
                score = Math.max(score, cacheMap.get(s) + process(end, str, --rest, cacheMap));
            }
        }
        return score;
    }

    public static int maxRecord2(String str, int K, String[] parts, int[] record) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        HashMap<String, Integer> cacheMap = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            cacheMap.put(parts[i], record[i]);
        }
        int N = str.length();
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i < K; i++) {
            dp[N][i] = -1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= K; j++) {
                int ans = -1;
                for (int end = i; end < N; end++) {
                    String s = str.substring(i, end + 1);
                    int next = j > 0 && cacheMap.containsKey(s) ? dp[end][j - 1] : -1;
                    if (cacheMap.containsKey(s)) {
                        ans = Math.max(ans, cacheMap.get(s) + next);
                    }
                    dp[i][j] = ans;
                }
            }
        }
        return dp[0][K];
    }
}
