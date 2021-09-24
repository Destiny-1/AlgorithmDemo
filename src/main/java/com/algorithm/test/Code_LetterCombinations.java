package com.algorithm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

/**
 * @Description: 电话号码的字符组合 https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * @author: zyu
 * @date: 2021年07月30日 15:56
 */
public class Code_LetterCombinations {
    public static String[] base = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public static String str = "";
    public static List<String> result = new ArrayList<>();

    public static List<String> letterCombinations(String digits) {
        str = digits;
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        process(digits, 0, new StringBuffer());
        return result;
    }

    /***
     * 目标数组 从0开始 遍历每一个字符获取数据
     * @param digits
     * @param index
     * @param stringBuffer
     */
    private static void process(String digits, int index, StringBuffer stringBuffer) {
        if (index == str.length()) {
            result.add(stringBuffer.toString());
            return;
        }
        char c = digits.charAt(index);

        //'2'转为int为50
        String phoneStr = base[(int) c - '2'];
        for (int i = 0; i < phoneStr.length(); i++) {
            //选phoneStr.charAt(i) 作为index位置的这个字符
            stringBuffer.append(phoneStr.charAt(i));
            process(digits, index + 1, stringBuffer);
            //清空之前的选择 做下一个选择
            stringBuffer.deleteCharAt(index);
        }
    }

    public static void main(String[] args) {
//        List<String> strings = letterCombinations("23");
        char c = '2';
        char b = '3';
        System.out.println((int) (b - c));
    }
}
