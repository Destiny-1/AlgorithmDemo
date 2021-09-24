package com.algorithm.test;

import java.util.Stack;

/**
 * @Description: 最长有效括号  https://leetcode-cn.com/problems/longest-valid-parentheses/
 * @author: zyu
 * @date: 2021年08月05日 10:51
 */
public class Code_LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        int max = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                max = Math.max(max, left * 2);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                max = Math.max(max, right * 2);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "(())((()())))()()()()";
        String s1 = "()()()()";
        String s2 = ")()())()()(";
        String s3 = ")()())";
        String s4 = "(()(((()";
//        System.out.println(longestValidParentheses(s));
//        System.out.println(longestValidParentheses(s1));
//        System.out.println(longestValidParentheses(s2));
//        System.out.println(longestValidParentheses(s3));
        System.out.println(longestValidParentheses(s4));
    }
}
