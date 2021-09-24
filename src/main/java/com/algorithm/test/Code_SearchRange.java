package com.algorithm.test;

/**
 * @Description: 在排序数组中查找元素的第一个和最后一个位置  https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @author: zyu
 * @date: 2021年08月06日 15:21
 */
public class Code_SearchRange {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int N = nums.length - 1;
        if (nums.length == 0 || nums[0] > target || nums[N] < target) {
            return result;
        }
        if (nums[0] == nums[N] && nums[0] == target) {
            result[0] = 0;
            result[1] = N;
            return result;
        }
        int L = nums[0] == target ? 0 : Integer.MAX_VALUE;
        int R = nums[N] == target ? N : -1;
        int index = binarySearch(nums, 0, N, target);

        if (index == -1) {
            return result;
        }
        L = Math.min(L, index);
        R = Math.max(R, index);
        for (int i = L; i >= 0; i--) {
            if (nums[i] != target) {
                L = i + 1;
                break;
            }
        }

        for (int i = R; i <= N; i++) {
            if (nums[i] != target) {
                R = i - 1;
                break;
            }
        }
        result[0] = L;
        result[1] = R;
        return result;

    }

    private static int binarySearch(int[] nums, int start, int end, int target) {
        if (start == end) {
            return nums[end] == target ? end : -1;
        }
        int mid = (start + end) >> 1;
        if (nums[mid] == target) {
            return mid;
        }
        int result = -1;
        if (nums[mid] > target) {
            result = binarySearch(nums, start, mid, target);
        } else {
            result = binarySearch(nums, mid + 1, end, target);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 2};
//        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 2;
        int[] ints = searchRange(nums, target);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
