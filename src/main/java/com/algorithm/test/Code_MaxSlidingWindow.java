package com.algorithm.test;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Description: 滑动窗口最大值 https://leetcode-cn.com/problems/sliding-window-maximum/
 * @author: zyu
 * @date: 2021年09月12日 13:55
 */
public class Code_MaxSlidingWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        int[] ret = new int[N - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            if (i + 1 >= k) {
                ret[i - k + 1] = nums[queue.peek()];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, -1};
        int k = 1;
        int[] ints = maxSlidingWindow(arr, k);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
