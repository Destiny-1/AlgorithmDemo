package com.algorithm.test;

/**
 * @Description: 下一个更大的排列  https://leetcode-cn.com/problems/next-permutation/
 * @author: zyu
 * @date: 2021年08月04日 16:35
 */
public class Code_NextPermutation {

    public static void nextPermutation(int[] nums) {
        int N = nums.length;

        if (N == 1) {
            return;
        }
        //满足条件直接返回 满足条件的情况下 N-1是最小的值
        for (int i = N - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int changeIndex = getFirtMaxIndex(N - 1, i, nums[i - 1], nums);
                swap(changeIndex, i - 1, nums);
                reverse(i, N - 1, nums);
                return;
            }
        }
        //都不满足表示是降序序列 全数组翻转
        reverse(0, N - 1, nums);
    }

    //寻找从后道歉遍历过的元素中第一个比他大的
    private static int getFirtMaxIndex(int start, int end, int num, int[] nums) {
        for (int i = start; i > end; i--) {
            if (nums[i] > num) {
                return i;
            }
        }
        return end;
    }

    //数组下标从0-n进行翻转
    public static void reverse(int start, int end, int[] nums) {
        //都不满足表示这个已经是降序排列的了 需要将数组翻转一下
        int index = end;
        for (int i = start; i < (end + start + 1) / 2; i++) {
            swap(i, index, nums);
            index--;
        }
    }

    public static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2, 3, 1};
//        int[] nums = new int[]{1, 3, 2};
//        int[] nums = new int[]{5, 4, 7, 5, 3, 2};
        int[] nums = new int[]{1, 5, 1};
//        int[] nums = new int[]{1};
        nextPermutation(nums);
//        reverse(2, 4, nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
