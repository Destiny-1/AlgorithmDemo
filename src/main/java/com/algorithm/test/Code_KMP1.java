package com.algorithm.test;

/**
 * @Description: kmp算法学习
 * @author: zyu
 * @date: 2021年08月19日 16:16
 */
public class Code_KMP1 {

    public static int kmp1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() < s2.length()) {
            return -1;
        }

        int left = s1.length();
        int right = s2.length();
        int[] next = getNextArr(s2);
        int i = 0;
        int j = 0;
        while (i < left && j < right) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else if (next[j] >= 0) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j == right ? i - right : -1;
    }

    /***
     * 获取每个字符除自己之外的最大公共前缀和最大公共后缀的长度 比如 abac c的next值为1 因为除去c之外第一个a和最后一个a相等
     * @param s2
     * @return
     */
    private static int[] getNextArr(String s2) {
        int[] next = new int[s2.length()];
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int count = 0;
        while (index < s2.length()) {
            if (s2.charAt(index - 1) == s2.charAt(count)) {
                next[index++] = ++count;
            } else if (count > 0) {
                count = next[count];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }
}
