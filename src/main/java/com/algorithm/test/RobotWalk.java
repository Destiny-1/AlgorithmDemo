package com.algorithm.test;

/**
 * @Description: 机器人走路问题解决方法 包含暴力递归 也包含动态规划
 * @author: zyu
 * @date: 2021年06月11日 10:47
 * <p>
 * 问题描述：
 * 已知一个长度为N N表示位置的最大范围为 1 - N 一个机器人所在位置为cur  目标位置为target  剩余步数为rest
 * 求机器人从cur用rest步走到target一共有几种情况
 * 当机器人在1位置时  它只能走到2位置  当机器人在N位置时  他只能走到 N-1位置 在其他位置的时候 可以向左走或者向又走
 * <p>
 * 暴力递归能改动态规划的前提是 暴力递归中有重复计算的部分
 * f(cur, rest)
 * 如 f(2, 4) 依赖 f(2,3)和f(1,3)   f(2,3) 依赖 f(1,2) 和f(3,2) f(1,3) 依赖 f(2,2)
 * f(1,2) 依赖 f(2,1) 而 f(3, 2) 也依赖f(2,1) 这样就出现了重复情况 就可以使用动态规划进行优化
 */
public class RobotWalk {
    /***
     *  利用暴力递归的方法解决
     * @param N  一共有多少个位置
     * @param cur  机器人当前位置
     * @param target  目标位置
     * @param rest  剩余步数
     * @return
     */
    public static int ways1(int N, int cur, int target, int rest) {
        if (N < 2 || cur < 1 || target < 1 || rest < 0 || target > N || cur > N) {
            return -1;
        }

        return process1(N, cur, target, rest);
    }

    private static int process1(int N, int cur, int target, int rest) {
        //base case 递归结束条件 当步数剩余为0时 如果当前位置和目标位置不一致就返回 0 一致就返回1
        if (rest == 0) {
            return cur == target ? 1 : 0;
        }
        //当当前的位置在最左边时  下一步只能往右走
        if (cur == 1) {
            return process1(N, 2, target, rest - 1);
        }

        //当当前的位置在最右边是 下一步只能往左走
        if (cur == N) {
            return process1(N, N - 1, target, rest - 1);
        }
        //当当前位置即不在最左边也不在最右边时 可以往两边走， 所以可能结果是两种情况之和
        return process1(N, cur + 1, target, rest - 1) + process1(N, cur - 1, target, rest - 1);
    }


    public static int ways2(int N, int cur, int target, int rest) {
        if (N < 2 || cur < 1 || target < 1 || rest < 0 || target > N || cur > N) {
            return -1;
        }

        //用来缓存cur和rest之间的关系的  已知cur的取值范围为1 - N  rest
        int[][] dp = new int[N + 1][rest + 1];
        //当cur == target rest=0时满足条件
        dp[target][0] = 1;
        return process2(N, cur, target, rest, dp);
    }

    private static int process2(int N, int cur, int target, int rest, int[][] dp) {
        //如果之前已经计算过了就可以直接从缓存拿值不用再重复计算了
        if (dp[cur][rest] != 0) {
            return dp[cur][rest];
        }
        int ans = 0;
        //base case 递归结束条件 当步数剩余为0时 如果当前位置和目标位置不一致就返回 0 一致就返回1
        if (rest == 0) {
            ans = cur == target ? 1 : 0;
        } else if (cur == 1) {
            //当当前的位置在最左边时  下一步只能往右走
            ans = process2(N, 2, target, rest - 1, dp);
        } else if (cur == N) {
            //当当前的位置在最右边是 下一步只能往左走
            ans = process2(N, N - 1, target, rest - 1, dp);
        } else {
            //当当前位置即不在最左边也不在最右边时 可以往两边走， 所以可能结果是两种情况之和
            ans = process2(N, cur + 1, target, rest - 1, dp) + process2(N, cur - 1, target, rest - 1, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }


    public static int ways3(int N, int cur, int target, int rest) {
        if (N < 2 || cur < 1 || target < 1 || rest < 0 || target > N || cur > N) {
            return -1;
        }
        int[][] dp = new int[N + 1][rest + 1];
        //初始化第0列
        dp[target][0] = 1;

        //初始化第一列到第rest列
        for (int i = 1; i <= rest; i++) {
            //cur在最左边 他下一步只能向右移动 所以他的值等于 cur = 2 rest = rest -1 时的值
            dp[1][i] = dp[2][i - 1];
            //可以左右都移动
            for (int j = 2; j < N; j++) {
                dp[j][i] = dp[j -1][i - 1] + dp[j + 1][i - 1];
            }
            //cur在最右边 他下一步只能向左移动 所以他的值等于 cur = N -1 rest = rest -1 时的值
            dp[N][i] = dp[N - 1][i - 1];
        }

        return dp[cur][rest];
    }

    public static void main(String[] args) {
        System.out.println(ways1(4, 2, 3, 3));
        System.out.println(ways2(4, 2, 3, 3));
        System.out.println(ways3(4, 2, 3, 3));
    }
}
