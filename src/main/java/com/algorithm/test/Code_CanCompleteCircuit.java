package com.algorithm.test;

/**
 * @Description: 加油站 https://leetcode-cn.com/problems/gas-station/
 * @author: zyu
 * @date: 2021年08月25日 14:49
 */
public class Code_CanCompleteCircuit {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int result = -1;
        int N = gas.length;
        //从哪个节点开始
        for (int i = 0; i < gas.length; i++) {
            int g = 0;
            int index = 0;
            int R = i;
            while (index != N) {
                int a = R % N;
                g += gas[a];
                if (g < cost[a]) {
                    break;
                }
                g -= cost[a];
                R++;
                index++;
            }
            if (index == N) {
                result = i;
                break;
            } else {
                i = i + index;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int [] gas = new int[]{2,3,4};
//        int[] cost = new int[]{3,4,3};
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
