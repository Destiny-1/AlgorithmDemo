package com.algorithm.test;

/**
 * @Description: 买卖股票的最佳时机  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * @author: zyu
 * @date: 2021年08月27日 10:36
 */
public class Code_MaxProfit {

    public static int maxProfit1(int[] prices) {
        int max = 0;
        if (prices.length == 1) {
            return max;
        }
        int N = prices.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                max = Math.max(prices[j] - prices[i], max);
            }
        }
        return max;
    }

    public static int maxProfit(int[] prices) {
        int max = 0;
        int N = prices.length;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > max) {
                max = prices[i] - minPrice;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(arr));
    }
}
