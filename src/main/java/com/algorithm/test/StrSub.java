package com.algorithm.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Description: 字符串的所有子序列
 * @author: zyu
 * @date: 2021年06月10日 13:49
 */
public class StrSub {

    /****
     *  作用是获取所有字符串的子序列 如abc的子序列 有"" a b c ab等
     *  解题思路  从左到右依次遍历字符串 每一步都做判断 选择要或者不要这个字符串 遍历到最后就是答案
     * @param s
     * @return
     */
    public static List<String> subStr1(String s) {
        List<String> ans = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        String path = "";
        char[] str = s.toCharArray();
        process1(str, 0, path, ans);
        return ans;
    }

    /****
     * str 表示的是目标字节数组
     * index 表示当前字节所处位置
     * path表示当前字节之前已经做过的决定 如 abcd 到c这个字符时 ab两个字节 留下了a 那么path = a
     * ans表示所有的结果
     * 此方法是用来递归遍历所有情况的
     * 需要注意的是 此方法的结果中会出现重复的字符串
     * @param str
     * @param index
     * @param path
     * @param ans
     */
    private static void process1(char[] str, int index, String path, List<String> ans) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        process1(str, index + 1, path + str[index], ans);
        process1(str, index + 1, path, ans);
    }

    public static HashSet<String> subStr2(String s) {
        HashSet<String> ans = new HashSet<String>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        String path = "";
        char[] str = s.toCharArray();
        process2(str, 0, path, ans);
        return ans;
    }

    private static void process2(char[] str, int index, String path, HashSet<String> ans) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        process2(str, index + 1, path + str[index], ans);
        process2(str, index + 1, path, ans);
    }

    public static void main(String[] args) {
        String s = "aacc";
        List<String> list = subStr1(s);
        for (String str : list) {
            System.out.println("ans:" + str);
        }

        System.out.println("================");
        HashSet<String> hashSet = subStr2(s);
        for(String str : hashSet){
            System.out.println("ans:" + str);
        }
    }
}
