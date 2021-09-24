package com.algorithm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 括号生成  https://leetcode-cn.com/problems/generate-parentheses/
 * @author: zyu
 * @date: 2021年08月02日 14:50
 */
public class Code_GenerateParenthesis {

    public static List<String> result = new ArrayList<>();

    public static List<String> condition = new ArrayList<>();

    /***
     * 生成成都为n的所有可能的并且 有效的 括号组合
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        process(n, n, "");
        return result;
    }

    private static void process(int left, int right, String sb) {
        if (left == 0 && right == 0) {
            condition.add(sb);
            return;
        }

        //因为相等的情况都放左边 所以不可能出现 右边比左边小的情况
        if (left == right) {
            process(left - 1, right, sb + "(");
            //左括号放的多
        } else if (right > left) {
            if (left > 0) {
                process(left - 1, right, sb + "(");
            }
            process(left, right - 1, sb + ")");
        }
    }

    private static void process1(int left, int right, StringBuilder sb) {
        if (left == 0 && right == 0) {
            condition.add(sb.toString());
            return;
        }

        //因为相等的情况都放左边 所以不可能出现 右边比左边小的情况
        if (left == right) {
            process1(left - 1, right, sb.append("("));
            sb.deleteCharAt(sb.length() - 1);
            //左括号放的多
        } else if (right > left) {
            if (left > 0) {
                process1(left - 1, right, sb.append("("));
                sb.deleteCharAt(sb.length() - 1);
            }
            //每一个process都是一种全新的情况
            process1(left, right - 1, sb.append(")"));
            //删掉做过的选择
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        process(1, 1, "");
        for (String s : condition) {
            System.out.println(s);
        }
        Object o = new Object();
    }
}
