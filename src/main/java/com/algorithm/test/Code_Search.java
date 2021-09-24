package com.algorithm.test;

/**
 * @Description: 搜索旋转排序数组  https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * @author: zyu
 * @date: 2021年08月06日 14:02
 */
public class Code_Search {

    public static int search(int[] nums, int target) {
        //大于一的情况
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int len = nums.length - 1;
        int k = -1;
        //找出k的位置
        for (int i = len; i > 0; i--) {
            //查找的时候如果刚好遇到匹配的数据直接返回
            if (nums[i] == target || nums[i - 1] == target) {
                return nums[i] == target ? i : i - 1;
            }
            if (nums[i] < nums[i - 1]) {
                k = i - 1;
                break;
            }
        }

        if (k == -1) {
            return -1;
        }
        //最大值也不满足的情况直接返回
        if (nums[k] < target) {
            return -1;
        }
        //表示在(k + 1-len)翻转范围之内  这个范围内的数据是升序排列
        int result = -1;
        if (nums[len] > target) {
            if (nums[k + 1] > target) {
                return -1;
            }
            result = binarySearch(nums, k + 1, len, target);
            //在(0 -k)范围内查找  //这个范围内的数据也是升序排列
        } else {
            result = binarySearch(nums, 0, k, target);
        }
        return result;

    }

    //二分查找
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
        int[] nums = new int[]{3, 5, 1};
//        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 2;
        System.out.println(search(nums, target));
    }
}
