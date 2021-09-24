package com.algorithm.test;

/**
 * @Description: index数组 用来解决查询范围内数字累加和的 和前缀和数组作用一样
 * 但是区别就是 indextree可以以O(logN)的时间复杂度完成当某个位置的值改变的时候 完成整个数据结构的调整
 * @author: zyu
 * @date: 2021年08月04日 14:07
 */
public class Code_indexTree {

    public static class IndexTree {
        private int arrLen;

        private int[] indexArr;

        //index数组下标从1开始 0 位置放弃不用
        public IndexTree(int[] initArr) {
            arrLen = initArr.length + 1;
            indexArr = new int[arrLen];
            for (int i = 1; i < arrLen; i++) {
                add(i, initArr[i - 1]);
            }
        }

        /***
         * 在数组的size位置上加一个数
         * @param size
         * @param value
         */
        public void add(int size, int value) {
            while (size <= arrLen) {
                indexArr[size] += value;
                size += size & -size;
            }
        }

        /***
         * 查询 1-index范围内的累加和
         * @param index
         */
        public int query(int index) {
            int ret = 0;
            while (index > 0) {
                ret += indexArr[index];
                index -= index & -index;
            }
            return ret;
        }

        /****
         * 把某个位置的值变成一个值
         * @param size
         * @param value
         */
        public void update(int size, int value) {
            value = value - indexArr[size];
            while (size <= arrLen) {
                indexArr[size] += value;
                size += size & -size;
            }
        }
    }

    public static void main(String[] args) {
        int[] right = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

        IndexTree indexTree = new IndexTree(right);
        int query = indexTree.query(7);
        System.out.println(query);
    }
}
