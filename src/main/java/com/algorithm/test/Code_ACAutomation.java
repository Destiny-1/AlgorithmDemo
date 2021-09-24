package com.algorithm.test;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: ac自动机 主要作用是用来进行敏感词筛选过滤
 * @author: zyu
 * @date: 2021年08月04日 14:46
 */
public class Code_ACAutomation {

    public static class Node {
        private String end;

        private Node[] children;

        //当前节点是否是结束节点
        private Boolean endUse;

        //fail节点 用来进行匹配失败后的字符跳转
        private Node fail;

        public Node() {
            end = null;
            children = new Node[26];
            endUse = false;
            fail = null;
        }
    }

    public static class ACAutomation {
        Node root = new Node();

        /***
         * 将字符串加入前缀树
         * @param s
         */
        public void insert(String s) {
            char[] chars = s.toCharArray();
            Node next = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (next.children[index] == null) {
                    next.children[index] = new Node();
                }
                next = next.children[index];
            }
            next.end = s;
        }

        //宽度优先遍历 用队列   宽度优先遍历是用队列结构 先进先出  深度优先遍历是用栈结构 后劲先出
        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cfail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                Node[] children = cur.children;
                for (int i = 0; i < children.length; i++) {
                    if (children[i] != null) {
                        children[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            if (cfail.children[i] != null) {
                                children[i].fail = cfail.children[i];
                                break;
                            } else {
                                cfail = cfail.fail;
                            }
                        }
                        queue.add(cur.children[i]);
                    }
                }
            }
        }

        //检查content中是否存在敏感词汇
        public List<String> containWords(String content) {
            List<String> result = new ArrayList<>();

            char[] chars = content.toCharArray();
            Node cur = root;
            Node prev = null;

            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                //当当前分支没有这个字符的时候
                while (cur != root && cur.children[index] == null) {
                    cur = cur.fail;
                }

                cur = cur.children[index] != null ? cur.children[index] : root;
                prev = cur;

                while (prev != root) {
                    //之前已经统计过了
                    if (prev.endUse) {
                        break;
                    }

                    //判断是不是一个完整的字符串
                    if (prev.end != null) {
                        result.add(prev.end);
                        prev.endUse = true;
                    }

                    prev = prev.fail;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}
