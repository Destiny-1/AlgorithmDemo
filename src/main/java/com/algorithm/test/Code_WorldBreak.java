package com.algorithm.test;

import java.util.HashSet;

/**
 * @Description: 假设所有字符都是小写字母
 * 大字符串是str
 * arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次.
 * 使用arr中的单词有多少种拼接str的方式. 返回方法数.
 * @author: zyu
 * @date: 2021年09月16日 10:55
 */
public class Code_WorldBreak {

    public static int worldBreak1(String str, String[] arr) {
        HashSet<String> hashSet = new HashSet<>();
        for (String s : arr) {
            hashSet.add(s);
        }
        int N = str.length();
        return process(0, str, hashSet);
    }

    private static int process(int L, String str, HashSet<String> hashSet) {
        int N = str.length();
        if (L == N) {
            return 0;
        }
        int ways = 0;
        for (int end = L; end < N; end++) {
            String s = str.substring(L, end + 1);
            if (hashSet.contains(s)) {
                ways += process(L + 1, str, hashSet);
            }
        }
        return ways;
    }

    public static class Node {
        boolean isEnd;
        Node[] child = new Node[26];

        public Node() {
        }
    }

    public static int ways3(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        Node root = new Node();
        for (String s : arr) {
            char[] chs = s.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.child[index] == null) {
                    node.child[index] = new Node();
                }
                node = node.child[index];
            }
            node.isEnd = true;
        }
        return g(0, str, root);
    }

    public static int worldBreak(String str, String[] arr) {
        Node root = new Node();
        for (String s : arr) {
            Node node = root;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.child[index] == null) {
                    node.child[index] = new Node();
                }
                node = node.child[index];
            }
            node.isEnd = true;
        }

        return g(0, str, root);
    }

    private static int g(int L, String str, Node root) {
        if (L == str.length()) {
            return 1;
        }
        int ways = 0;
        Node cur = root;
        for (int end = L; end < str.length(); end++) {
            int path = str.charAt(end) - 'a';
            //字符串中需要的字符 数组中不存在
            if (cur.child[path] == null) {
                break;
            }
            cur = cur.child[path];
            if (cur.isEnd) {
                ways += g(L + 1, str, root);
            }
        }
        return ways;
    }

    public static void main(String[] args) {
        String str = "abbb";
        String[] arr = new String[]{"b"};
        System.out.println(worldBreak(str, arr));
        System.out.println(ways3(str, arr));
    }
}
