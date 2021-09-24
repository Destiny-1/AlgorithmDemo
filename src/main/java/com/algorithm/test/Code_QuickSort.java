package com.algorithm.test;

/**
 * @Description: 快速排序
 * @author: zyu
 * @date: 2021年08月31日 10:34
 */
public class Code_QuickSort {

    public static void quickSort(int[] arr) {
        int N = arr.length;
        if (N <= 1) {
            return;
        }
        sort(0, N - 1, arr);
    }

    private static void sort(int start, int end, int[] arr) {
        int[] neArr = netherland(start, end, arr);
        if (neArr[0] - 1 > start) {
            sort(start, neArr[0] - 1, arr);
        }

        if (neArr[1] + 1 < end) {
            sort(neArr[1] + 1, end, arr);
        }
    }

    private static int[] netherland(int start, int end, int[] arr) {
        int left = start - 1;
        int right = end;
        int index = start;
        while (index < right) {
            if (arr[index] > arr[end]) {
                //大于的时候有边界往左移
                swap(index, --right, arr);
            } else if (arr[index] == arr[end]) {
                index++;
            } else {
                //小于的时候左边界往右移
                swap(index++, ++left, arr);
            }
        }
        swap(index, end, arr);
        return new int[]{left + 1, right};
    }

    private static void swap(int left, int right, int[] arr) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 4, 6, 7, 3, 2, 1, 4};
        quickSort(arr);
        int[] ret = netherland(0, arr.length - 1, arr);
        System.out.println(ret[0]);
        System.out.println(ret[1]);
        for (int num : arr) {
            System.out.println(num);
        }
    }
}
