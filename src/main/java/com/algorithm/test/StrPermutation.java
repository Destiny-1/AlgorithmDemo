package com.algorithm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 字符串的所有排列组合情况
 * @author: zyu
 * @date: 2021年06月10日 14:47
 */
public class StrPermutation {


    public static List<String> permutation(String s) {
        List<String> ans = new ArrayList<String>();
        char[] str = s.toCharArray();
        List<Character> list = new ArrayList<Character>();
        for (char c : str) {
            list.add(c);
        }
        process1(list, 0, ans);
        return ans;
    }

    private static void process1(List<Character> list, int index, List<String> ans) {
        if (index == list.size()) {
            ans.add(list.toString());
            return;
        }

        //用来过滤重复元素
        Boolean[] characterBoolean = new Boolean[256];
        //此循环的目的是为了让字符串中的任何一个元素都可以当头元素
        for (int i = index; i < list.size(); i++) {
            Character character = list.get(i);
            if (characterBoolean[character] == null) {
                characterBoolean[character] = true;
                //表示当前位置和后面的元素互换位置
                swap(list, i, index);
                process1(list, index + 1, ans);
                //恢复换过之后的样子
                swap(list, index, i);
            }
        }
    }

    private static void swap(List<Character> list, int i, int index) {
        Character character = list.get(i);
        Character character1 = list.get(index);
        list.set(index, character);
        list.set(i, character1);
    }

    public static void main(String[] args) {
        List<String> list = permutation("aacc");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
