package com.algorithm.test;

/**
 * Manacher 算法的大体上的解决思路和暴利递归的一致，就是从左往右依次遍历数组的每个元素，判断当以此元素为中心时最长回文子串的长度是多少，
 * 都需要把原字符串预先处理为特殊的字符串 即 123 ---》 #1#2#3# 最终返回结果为（最长回文子串的长度 - 1）/2
 * Manacher 需要把之前已经处理过的结果保存起来，用来加速后面的过程
 * 首先需要定义 一个cur变量 表示当前所在位置，一个L变量表示中心点C位置的左边界 一个R变量表示回文子串所能扩到的最大右边界 一个p[]数组用来表示数组每个位置的最大回文半径 C 表示达到最大扩充边界R时中心点的位置
 * 整体分为两种情况：
 * 1: cur > R  这种情况 不能加速 只能暴力循环
 * 2：cur <= R  这种情况下又可以具体细分为三种情况
 * 2.1 [L L1 cur1   C    cur  R ]   cur1 是cur 关于 C的对称点    C是L -- R的中心  cur1 > L 即cur1 的左边界在L中 那么 p[cur] = p[cur1]
 * 2.2 [L1 L cur1 C cur R]   cur1 的左边界在L在外 即 L1 < L 那么p[cur] = R - cur
 * 2.3 cur1 的左边界和L重合是 即 [L1==L cur1 C cur R] 此时P[cur]至少为p[cur1] 然后再接着遍历
 *
 * @Description: Manacher算法 以O(N)时间复杂度来解决最长回文子串问题
 * @author: zyu
 * @date: 2021年07月27日 9:45
 */
public class Code_Manacher {

    /***
     * "123" ---> "#1#2#3#"
     * @param s
     * @return
     */
    public static char[] manacherString(String s) {
        char[] c = new char[s.length() * 2 + 1];
        int j = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            c[j++] = '#';
            c[j++] = chars[i];
        }
        c[j++] = '#';
        return c;
    }

    /***
     * 求解字符串的最长回文子串的长度
     * @param s
     * @return
     */
    public static int manacher(String s) {
        //结果
        int result = Integer.MIN_VALUE;
        char[] c = manacherString(s);
        //以当前字符为中心点时最大回文半径是多少  如 #1#2#2#1# 以最中间的那个#为中心点 最大回文半径为 5即#2#1#
        int p[] = new int[c.length];
        //最大能达到的右边界 + 1
        int R = -1;
        //达到最大右边界时的中心点位置
        int C = -1;
        for (int cur = 0; cur < c.length; cur++) {
            //当前位置小于R位置中情况2 大于等于中情况一
            //2*C - cur 表示的是cur1的位置
            //p[2*C - cur] 表示的是 cur关于c的对称点cur1 的左边界在L之内
            // R - cur 表示的是 cur关于c的对称点cur1的左边界在L之外
            //无论哪种情况 取的都是两者的最小值 当cur1的左边界等于L时 可以继续遍历 则走下面的while流程
            p[cur] = cur < R ? Math.min(p[2 * C - cur], R - cur) : 1;
            while (cur + p[cur] < c.length && cur - p[cur] > -1) {
                if (c[cur + p[cur]] == c[cur - p[cur]]) {
                    p[cur]++;
                } else {
                    break;
                }
            }
            //当前最大边界超过R
            if (cur + p[cur] > R) {
                R = cur + p[cur];
                C = cur;
            }
            result = Math.max(result, p[cur]);

            //当r括到最右边界时 可以结束了
            if (R == c.length) {
                return result - 1;
            }
        }
        return result - 1;
    }

    /***
     * 正确的方法 用作对数器
     * @param s
     * @return
     */
    public static int right(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        char[] c = manacherString(s);
        int result = 0;
        for (int i = 0; i < c.length; i++) {
            int L = i;
            int R = i;
            int ans = 0;
            while (L > -1 && R < c.length) {
                if (c[L] == c[R]) {
                    ans = (L == R) ? ans + 1 : ans + 2;
                }
                L--;
                R++;
            }
            result = Math.max(result, ans);
        }
        return (result - 1) / 2;
    }


    public static void main(String[] args) {
        int manacher = right("121");
        System.out.println(manacher);
    }
}
