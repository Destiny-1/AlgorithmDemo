package com.algorithm.test;

import java.util.*;

/**
 * @Description: 字母异位词分组  https://leetcode-cn.com/problems/group-anagrams/
 * @author: zyu
 * @date: 2021年08月10日 13:51
 */
public class Code_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String s = String.valueOf(arr);
            map.getOrDefault(s, new ArrayList<>());
            List<String> list = map.get(s);
            list.add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        Code_GroupAnagrams code_groupAnagrams = new Code_GroupAnagrams();
        List<List<String>> lists = code_groupAnagrams.groupAnagrams(strs);
        for (List<String> str : lists) {
            System.out.println(str);
            System.out.println("----------------------");
        }
    }
}
