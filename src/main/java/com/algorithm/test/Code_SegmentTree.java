package com.algorithm.test;

/**
 * @Description: 线段树结构实现 主要是用来解决 范围内增加 修改 和查询 以O(logN)的时间复杂度
 * @author: zyu
 * @date: 2021年07月29日 10:07
 * <p>
 * 线段树结构 用数组表示  第一个元素表示整个  第二个表示他的左子树  第三个表示他的右子树  当前节点l的子树为 2*l 和 2*l + 1
 * 1---1000
 * 1-500          501 -- 1000
 * 1-250  251-500    501 --750   751 ---1000
 */
public class Code_SegmentTree {

    public static class Segment {
        //传进来数组的长度
        private int maxLen;

        private int[] arr;

        //累加和数组
        private int[] sum;

        //增加懒数组
        private int[] lazy;

        //更新数组
        private int[] change;

        //更新状态数组  为true表示对应的update数组是更新为某个数 为false则不是更新
        private Boolean[] update;

        public Segment(int[] origin) {
            //线段树数组下标都是从1开始 所以长度为arr.length + 1
            maxLen = origin.length + 1;
            arr = new int[maxLen];
            //赋初始值
            for (int i = 1; i < arr.length; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[maxLen << 2];
            lazy = new int[maxLen << 2];
            change = new int[maxLen << 2];
            update = new Boolean[maxLen << 2];
        }

        /***
         * 根据原始数组填充sum数组
         */
        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            //递归遍历左子树
            build(l, mid, rt << 1);
            //递归遍历又子树
            build(mid + 1, r, rt << 1 | 1);
            //计算rt的累加和
            pushUp(rt);
        }

        public void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        //更新方法 L --- R 这个范围内 每个数都变成C

        /***
         *
         * @param L  需要更新的左边界
         * @param R  需要更新的右边界
         * @param C  更新的值
         * @param l   当前操作的左边界
         * @param r   当前操作的右边界
         * @param rt   当前所属的位置
         */
        public void update(int L, int R, int C,
                           int l, int r, int rt) {
            //需要更新的位置包含当前的所有位置 结束循环
            if (L <= l && R >= r) {
                change[rt] += C;
                update[rt] = true;
                sum[rt] = (r - l + 1) * C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }

            if (R > mid && L < r) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        /***
         * L 到 R范围内 每个元素加c
         * @param L
         * @param R
         * @param C
         * @param l
         * @param r
         * @param rt
         */
        public void add(int L, int R, int C,
                        int l, int r, int rt) {
            //需要更新的位置包含当前的所有位置
            if (L <= l && R >= r) {
                lazy[rt] += C;
                sum[rt] += (r - l + 1) * C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }

            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        /***
         * L--R 范围内查累加和
         * @param L
         * @param R
         * @param l
         * @param r
         * @param rt
         */
        public long query(int L, int R,
                          int l, int r, int rt) {

            //需要更新的位置包含当前的所有位置
            if (L <= l && R >= r) {
                return sum[rt];
            }
            long result = 0;
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                result += query(L, R, l, mid, rt << 1);
            }

            if (R > mid && L < r) {
                result += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return result;
        }

        //判断当前坐标是否有任务 有任务的话下发一层
        //ln左子树的个数 rn右子树的个数
        private void pushDown(int rt, int ln, int rn) {
            if (update[rt] != null && update[rt]) {
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                change[rt] = 0;
                update[rt] = false;
            }

            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }
    }

    /***
     * 生成长度为size的 最大值为max的数组
     * @param size
     * @param max
     * @return
     */
    public static int[] genRandomArr(int size, int max) {
        int[] ints = new int[size];

        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * max) + 1;
        }
        return ints;
    }

    public static class Right {
        private int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 1; i < arr.length; i++) {
                arr[i] = origin[i - 1];
            }
        }

        public void add(int L, int R, int C) {
            if (L < 0 || L >= arr.length || R < 0) {
                return;
            }
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public void update(int L, int R, int C) {
            if (L < 0 || L >= arr.length || R < 0) {
                return;
            }
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public int query(int L, int R) {
            if (L < 0 || L >= arr.length || R < 0) {
                return 0;
            }
            int result = 0;
            for (int i = L; i <= R; i++) {
                result += arr[i];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 5;
        int[] ints = genRandomArr(size, max);
        Right right = new Right(ints);
        Segment segment = new Segment(ints);
        int L = 1;
        int R = ints.length;
        int root = 1;
        segment.build(L, R, root);
        right.add(1, 9, 6);
        segment.add(1, 9, 6, L, R, root);
        right.update(1, 9, 5);
        segment.update(1, 9, 5, L, R, root);
        int a = right.query(1, 9);
        long b = segment.query(1, 9, L, R, root);
        System.out.println(a);
        System.out.println(b);
    }
}
