package com.algorithm.test;

/**
 * @Description: 实现 Trie (前缀树) https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * @author: zyu
 * @date: 2021年09月07日 14:42
 */
public class Code_Trie {
    public static class Trie {
        private Trie[] children;
        private boolean isEnd;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            char[] chars = word.toCharArray();
            Trie node = this;
            int index = -1;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie trie = searchPrefix(word);
            return trie != null && trie.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            char[] chars = prefix.toCharArray();
            Trie node = this;
            int index = -1;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        //true
        System.out.println(trie.search("apple"));
        //false
        System.out.println(trie.search("app"));
        //true
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        //true
        System.out.println(trie.search("app"));
    }
}
