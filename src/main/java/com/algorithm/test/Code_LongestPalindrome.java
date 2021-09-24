package com.algorithm.test;

/**
 * @Description: 最长回文子串  https://leetcode-cn.com/problems/longest-palindromic-substring/
 * @author: zyu
 * @date: 2021年07月27日 16:51
 */
public class Code_LongestPalindrome {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 0) {
            return "";
        }
        char[] c = manacherString(s);
        String result = "";
        int C = -1;
        int R = -1;
        //最大值
        int size = 0;
        int maxCur = -1;
        int p[] = new int[c.length];
        for (int cur = 0; cur < c.length; cur++) {
            p[cur] = cur < R ? Math.min(p[2 * C - cur], R - cur) : 1;
            while (cur + p[cur] < c.length && cur - p[cur] > -1) {
                if (c[cur + p[cur]] == c[cur - p[cur]]) {
                    p[cur]++;
                } else {
                    break;
                }
            }

            if (cur + p[cur] > R) {
                R = cur + p[cur];
                C = cur;
            }
            if (p[cur] > size) {
                size = p[cur];
                maxCur = cur;
            }
        }
        int left = maxCur - p[maxCur];
        int right = maxCur + p[maxCur];
        for (int i = left + 1; i < right; i++) {
            if ((i & 1) != 0) {
                result += c[i];
            }
        }
        return result;
    }

    /***
     * 将"123" ----> "#1#2#3#"
     * @param s
     * @return
     */
    public static char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] c = new char[2 * s.length() + 1];
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            c[j++] = '#';
            c[j++] = chars[i];
        }
        c[j] = '#';
        return c;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("zwerabbarss"));
    }
}
