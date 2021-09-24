package com.algorithm.test;

/**
 * 简介：当序列的规模为N 采样的数量为K 时 确保1-N范围内每个数被取样的概率都是一致的  N可能动态改变
 *
 * @Description: 蓄水池算法   主要应用范围是抽奖
 * @author: zyu
 * @date: 2021年07月28日 15:40
 */
public class Code_Reservoir {

    /***
     * 蓄水池算法
     *
     * 1：创建一个长度为bag的数组 用来保存结果 bagArr[i] 表示的是第i个背包中保存的球的序号是多少
     * 2：从1-ballNumer进行遍历 当数小于bag的长度时 直接放入容器中 ，
     * 大于bag的话 则根据随机算法在1-index范围内等概率获取随机数x (index表示的是当前来到数) 当x <= bag时
     * 调用随机算法获取0-bag-1范围内的任意随机数 z 然后让bag[z] = index
     * @param ballNumber 问题规模
     * @param bag 采样规模
     */
    public static void reservoir(int ballNumber, int bag) {
        int test = 1_000_000;
        int count[] = new int[ballNumber + 1];
        for (int j = 0; j < test; j++) {
            int bagArr[] = new int[bag];
            int bagIndex = 0;
            for (int num = 1; num <= ballNumber; num++) {
                //前十个直接放袋子里
                if (num <= bag) {
                    bagArr[bagIndex++] = num;
                } else {
                    //1-num等概率获取随机数
                    int random = getRandom(num);
                    //随机数为小于等于十的时候直接替换
                    if (random <= bag) {
                        bagIndex = (int) (Math.random() * bag);
                        bagArr[bagIndex] = num;
                    }
                }
            }
            for (int i : bagArr) {
                count[i]++;
            }
        }

        // 1- ballNumber 范围内的每个数 被取样的概率
        for (int i = 0; i <= ballNumber; i++) {
            System.out.println(i + ":" + count[i]);
        }
    }

    private static int getRandom(int seq) {
        return (int) ((Math.random() * seq) + 1);
    }

    public static void main(String[] args) {
        reservoir(17, 10);
    }
}
