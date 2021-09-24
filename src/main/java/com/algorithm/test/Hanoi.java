package com.algorithm.test;

/**
 * @Description: 汉诺塔问题解决办法
 * @author: zyu
 * @date: 2021年06月10日 11:37
 */
public class Hanoi {

    /***
     * 汉诺塔问题解决办法1
     * @param n
     */
    public static void Hanoi1(int n) {
        if (n <= 0) {
            return;
        }
        leftToRight(n);
    }


    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("move " + n + " from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("move " + n + " from left to right");
        midToRight(n - 1);
    }

    private static void midToRight(int n) {
        if (n == 1) {
            System.out.println("move " + n + " from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("move " + n + " from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("move " + n + " from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("move " + n + " from right to left");
        midToLeft(n - 1);
    }

    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("move " + n + " from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("move " + n + " from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    /***
     * 汉诺塔问题解决办法1
     * @param n
     */
    public static void Hanoi2(int n) {
        if (n <= 0) {
            return;
        }
        f(n, "left", "right", "mid");
    }

    /***
     * 几个块从from点移到to点 中间点位mid
     * @param n
     * @param from
     * @param to
     * @param mid
     */
    private static void f(int n, String from, String to, String mid) {
        if (n == 1) {
            System.out.println(String.format("move %s from %s to %s", n, from, to));
        } else {
            f(n - 1, from, mid, to);
            System.out.println(String.format("move %s from %s to %s", n, from, to));
            f(n - 1, mid, to, from);
        }
    }

    public static void main(String[] args) {
        Hanoi1(3);
        System.out.println("=============================");
        Hanoi2(3);
    }
}
