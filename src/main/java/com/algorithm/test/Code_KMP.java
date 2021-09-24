package com.algorithm.test;

/**
 *
 * 作用类似为string.indexof()
 * N 为S1的长度  M为S2的长度   求S1中第一次出现S2子串的索引下标位置 没有的话返回-1
 * 如 S1 = abcdabcefg S2 = abce  那么返回 4 表示下标为四开始是第一次出现相同子串的位置 注 相同子串的位置一定是连续的
 * kmp算法是为了解决两个字符串的公共子串的问题  由原本暴利递归的O(M*N) 变成O(N)的时间复杂度
 *
 * @Description: kmp算法
 * @author: zyu
 * @date: 2021年07月14日 10:06
 */
public class Code_KMP {


    /***
     * kmp算法主体实现
     * @param s1
     * @param s2
     * @return
     */
    public static int kmp(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();


        int[] next = getNextValue(arr2);
        int x = 0;
        int y = 0;
        while (x < arr1.length && y < arr2.length) {
            //表示x y的值相等 同是比较下一个
            if (arr1[x] == arr2[y]) {
                x++;
                y++;
                //x y 的值不等 x不动 将y往前移  next[y] 表示的是当前y字符从头开始的第一个出现y的下一个
            } else if (next[y] != -1) {
                y = next[y];
                //移到第一个时还是不等 则 x往后移
            } else {
//                y = 0;   //经过上面的循环 y 最终会变成0 不用额外设置了
                x++;
            }
        }

        return y == arr2.length ? x - y : -1;
    }

    /**
     * 获取arr2中每个数 不包含当前数的情况下 最大前缀子串和最大后缀子串最大相同长度是多少 不包含全量的
     * next数组的具体含义就是当前元素从哪个位置开始于自己前一个数进行比较 保存之后有利于后期的加速
     * <p>
     * 如 abcdeabcf f位置的最大长度子串为 3  即前三个abc 和f之前的abc相等
     *
     * @param arr2
     * @return
     */
    public static int[] getNextValue(char[] arr2) {
        int[] next = new int[arr2.length];
        next[0] = -1;
        next[1] = 0;
        //next下标
        int i = 2;
        int count = 0;
        while (i < arr2.length) {
            //arr[i -1] 表示的是当前数的前一个数
            //count表示的是 当前数的前一个数的 next的值 count = next[arr[i -1]] 即当前哪个位置的值和i-1位置进行比较
            //arr[count] 表示的就是当前数的前一个数的最大公共长度的下一个数 如果两者相等 则 当前数的next 为前一个数next + 1
            if (arr2[i - 1] == arr2[count]) {
                next[i++] = ++count;
            } else if (count > 0) {
                count = next[count];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(1^1^2);
    }
}
