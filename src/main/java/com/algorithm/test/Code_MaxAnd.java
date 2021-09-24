package com.algorithm.test;

/**
 * @Description: 给定一个非负数组成的数组，长度一定大于1
 * 想知道数组中哪两个数&的结果最大
 * 返回这个最大结果
 * @author: zyu
 * @date: 2021年09月15日 15:15
 */
public class Code_MaxAnd {

    public static int maxAnd(int[] arr) {
        int R = arr.length;
        int ans = 0;
        for (int bit = 30; bit >= 0; bit--) {
            int tmp = R;
            int index = 0;
            while (index < R) {
                //判断第bit位是否为1   第一种 arr[index] & (1 << bit)
                //第二种 (arr[index] >> bit ) & 1
                if (((arr[index] >> bit) & 1) == 0) {
                    swap(index, arr, --R);
                } else {
                    index++;
                }
            }
            if (R == 2) {
                return arr[0] & arr[1];
            }
            if (R < 2) {
                R = tmp;
            } else {
                ans |= (1 << bit);
            }
        }
        return ans;
    }

    //暴力解
    public static int maxAndValue1(int[] arr) {
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                max = Math.max(max, arr[i] & arr[j]);
            }
        }
        return max;
    }

    private static void swap(int index, int[] arr, int i) {
        int tmp = arr[index];
        arr[index] = arr[i];
        arr[i] = tmp;
    }

    public static int[] randomArray(int size, int range) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * range) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int range = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * maxSize) + 2;
            int[] arr = randomArray(size, range);
            int ans1 = maxAndValue1(arr);
            int ans2 = maxAnd(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }
}
