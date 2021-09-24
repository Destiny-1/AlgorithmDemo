package com.algorithm.test;

import java.util.*;

/**
 * @Description: 合并区间  https://leetcode-cn.com/problems/merge-intervals/
 * @author: zyu
 * @date: 2021年08月12日 14:17
 */
public class Code_Merge {

    public static int[][] merge(int[][] intervals) {
        insertSort(intervals);
        int N = intervals.length;
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            //后一个
            int[] arr = intervals[i];
            while (!stack.isEmpty()) {
                //前一个
                int[] peek = stack.peek();
                //前一个的范围大  peek: [1,5] arr:[2,4]这种情况
                boolean leftBig = peek[1] >= arr[0] && peek[0] <= arr[0];
                //后一个范围大  peek: [2,9] arr: [1,5]这种情况
                boolean rightBig = arr[1] >= peek[0] && arr[0] <= peek[0];
                if (leftBig || rightBig) {
                    arr[0] = Math.min(peek[0], arr[0]);
                    arr[1] = Math.max(peek[1], arr[1]);
                    stack.pop();
                    continue;
                } else {
                    break;
                }
            }
            stack.push(arr);
        }

        int[][] result = new int[stack.size()][2];
        int size = stack.size();
        while (!stack.isEmpty()) {
            result[--size] = stack.pop();
        }
        return result;
    }

    public static int[][] merge1(int[][] intervals) {
        insertSort(intervals);
        List<int[]> merged = new ArrayList<int[]>();
        //经过start区间排序的时候  前面的L 一定是大于后面的L
        // 这样就只有两种情况了 第一种 后面的L 小于前面的R 第二种后面的L 大于前面的R
        // 所以如果需要合并的话 只有一种可能 后面的L小于前面的R
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0];
            int R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void insertSort(int[][] intervals) {
        for (int i = 1; i < intervals.length; i++) {
            //已经有序的部分
            for (int j = 0; j < i; j++) {
                if (intervals[j][0] > intervals[i][0]) {
                    int[] temp = intervals[i];
                    intervals[i] = intervals[j];
                    intervals[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[][] arr = new int[][]{{1, 4}, {0, 1}};
//        int[][] arr = new int[][]{{1, 4}, {4, 5}};
//        int[][] arr = new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
//        int[][] arr = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] arr = new int[][]{{4, 99}, {5, 5}, {2, 2}, {3, 4}, {3, 4}};
        int[][] merge = merge(arr);
        for (int i = 0; i < merge.length; i++) {
            System.out.println(merge[i][0] + ":" + merge[i][1]);
        }
    }
}
