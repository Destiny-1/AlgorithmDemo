package com.algorithm.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 最小覆盖子串  https://leetcode-cn.com/problems/minimum-window-substring/
 * @author: zyu
 * @date: 2021年08月17日 16:30
 */
public class Code_MinWindow {

    public static String minWindow(String s, String t) {
        Map<Character, Integer> result = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();
        //统计t中每个字符的数量
        for (int i = 0; i < t.length(); i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        int L = 0;
        int R = -1;
        int N = s.length();
        int resultL = -1;
        int resultR = -1;
        int size = Integer.MAX_VALUE;
        while (R < N) {
            ++R;
            if (R < N && count.containsKey(s.charAt(R))) {
                result.put(s.charAt(R), result.getOrDefault(s.charAt(R), 0) + 1);
            }
            while (check(result, count) && L <= R) {
                //目前窗口是否是最小值
                if (R - L + 1 < size) {
                    size = R - L + 1;
                    resultL = L;
                    resultR = L + size;
                }
                //回收的元素
                if (count.containsKey(s.charAt(L))) {
                    result.put(s.charAt(L), result.get(s.charAt(L)) - 1);
                }
                L++;
            }
        }
        return resultL == -1 ? "" : s.substring(resultL, resultR);
    }

    public static String minWindow1(String s, String t) {
        Map<Character, Integer> result = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();
        //统计t中每个字符的数量
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        int L = 0;
        int R = 0;
        int N = s.length();
        int resultL = -1;
        int resultR = -1;
        int size = Integer.MAX_VALUE;
        while (R < N) {
            // R向右扩
            if (count.containsKey(s.charAt(R))) {
                result.put(s.charAt(R), result.getOrDefault(s.charAt(R), 0) + 1);
            }
            //检查个数是否已经达标
            while (check(result, count) && L <= R) {
                //目前窗口是否是最小值
                if (R - L + 1 < size) {
                    size = R - L + 1;
                    resultL = L;
                    resultR = R + 1;
                }
                //尝试窗口左边界收缩
                if (count.containsKey(s.charAt(L))) {
                    result.put(s.charAt(L), result.get(s.charAt(L)) - 1);
                }
                L++;
            }
            R++;
        }
        return resultL == -1 ? "" : s.substring(resultL, resultR);
    }

    /***
     * 检查是否已经包含了所有元素
     * @return
     */
    private static boolean check(Map<Character, Integer> result, Map<Character, Integer> count) {
        Set<Character> countKey = count.keySet();
        for (Character key : countKey) {
            if (count.get(key) > result.getOrDefault(key, 0)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "a";
        System.out.println(minWindow1(s1, s2));

    }
}
