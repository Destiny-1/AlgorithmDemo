package com.algorithm.test;

/**
 * @Description: 卡牌得分最大问题
 * @author: zyu
 * @date: 2021年06月11日 14:19
 * <p>
 * 描述：
 * 在一条直线上有若干张卡牌 比如1.5.100.20.110.. 有两个人玩卡牌游戏 一个先拿一个后拿 拿牌的时候只能拿左边或者右边
 * 二者都决定聪明 并且可以看到牌的顺序 问 最后赢家的分数是多少
 */
public class CardWin {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int first = f(arr, 0, arr.length - 1);
        int second = g(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    /***
     * 先手从 L .. R范围内拿牌
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int f(int[] arr, int L, int R) {
        //如果只有一张的话 直接拿掉
        if (L == R) {
            return arr[L];
        }
        //拿左边一张 就等于后手在 L + 1 .. R 范围内拿的值
        int first = arr[L] + g(arr, L + 1, R);
        //拿右边一张 就等于后手在 L 。。 R -1范围拿
        int second = arr[R] + g(arr, L, R - 1);
        return Math.max(first, second);
    }

    /***
     * 后手从 L .. R 范围拿数据
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int g(int[] arr, int L, int R) {
        //只有一张牌的情况的 后手一定拿不到 所以返回0
        if (L == R) {
            return 0;
        }
        //表示先手的人拿了L处的牌  所以后手就可以转换为先手拿L+ 1 .. R 范围内的值
        int first = f(arr, L + 1, R);
        //表示先手的人拿了R处的牌  所以后手就可以转换为先手拿L1 .. R - 1范围内的值
        int second = f(arr, L, R - 1);
        //因为两个人都是绝顶聪明，都会让对手拿到尽可能小的牌 所以取最小值
        return Math.min(first, second);
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fMap = new int[N + 1][N + 1];
        int[][] gMap = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fMap[i][j] = -1;
                gMap[i][j] = -1;
            }
        }
        int first = f1(arr, 0, arr.length - 1, fMap, gMap);
        int second = g1(arr, 0, arr.length - 1, fMap, gMap);
        return Math.max(first, second);
    }

    /***
     * 先手从 L .. R范围内拿牌
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int f1(int[] arr, int L, int R, int[][] fMap, int[][] gMap) {
        if (fMap[L][R] != -1) {
            return fMap[L][R];
        }
        int ans = 0;
        //如果只有一张的话 直接拿掉
        if (L == R) {
            ans = arr[L];
        } else {
            //拿左边一张 就等于后手在 L + 1 .. R 范围内拿的值
            int first = arr[L] + g1(arr, L + 1, R, fMap, gMap);
            //拿右边一张 就等于后手在 L 。。 R -1范围拿
            int second = arr[R] + g1(arr, L, R - 1, fMap, gMap);
            ans = Math.max(first, second);
        }
        fMap[L][R] = ans;
        return ans;
    }

    /***
     * 后手从 L .. R 范围拿数据
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int g1(int[] arr, int L, int R, int[][] fMap, int[][] gMap) {
        //只有一张牌的情况的 后手一定拿不到 所以返回0
        if (L == R) {
            return 0;
        }
        //表示先手的人拿了L处的牌  所以后手就可以转换为先手拿L+ 1 .. R 范围内的值
        int first = f1(arr, L + 1, R, fMap, gMap);
        //表示先手的人拿了R处的牌  所以后手就可以转换为先手拿L1 .. R - 1范围内的值
        int second = f1(arr, L, R - 1, fMap, gMap);
        //因为两个人都是绝顶聪明，都会让对手拿到尽可能小的牌 所以取最小值 这个最大值最小值不是指的下一张的最大最小 而是剩下所有回合弄完之后的最大最小
        return Math.min(first, second);
    }

    /***
     * 最终程度动态规划 利用两个表之间的关系直接推导数据
     * @param arr
     * @return
     *
     *   以arr长度为5举例
     *   f(0,0) f(1,1) f(2,2)f(3,3)f(4,4)的值分别为 arr[0] arr[1] arr[2] arr[3]和arr[4]
     *   g(0,0) g(1,1) g(2,2)g(3,3)g(4,4)的值都是0
     *   又因为f(0,1)的值等于Math.max(g(0,0), g(1,1))
     *   即f(L,R)的值等于Math.max(g(L + 1, R), g(L, R -1))
     *   g(L,R)的值等于Math.min(f(L + 1, R), f(L, R -1))
     *   由于已经有了初始数据，所以两个表互相推导就可以算出所有的值
     *
     */
    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fMap = new int[N][N];
        int[][] gMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            fMap[i][i] = arr[i];
        }

        //遍历表的每条对角线遍历
        for (int i = 1; i < N; i++) {
            int col = 0;
            int row = i;
            while (row < N) {
                fMap[col][row] = Math.max(arr[col] + gMap[col + 1][row], arr[row] + gMap[col][row - 1]);
                gMap[col][row] = Math.min(fMap[col + 1][row], fMap[col][row - 1]);
                col++;
                row++;
            }
        }

        return Math.max(fMap[0][N - 1], gMap[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 97, 100, 1, 77, 22, 44};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }
}
