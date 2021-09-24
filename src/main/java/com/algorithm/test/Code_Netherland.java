package com.algorithm.test;

/**
 * @Description: 快速排序分区的荷兰国旗问题    小于基准值的在左边 等于基准值的在中间 大于基准值的在右边
 * @author: zyu
 * @date: 2021年08月11日 10:59
 */
public class Code_Netherland {

    /****
     * 在arr数组的 L... R范围内 小于arr[R]的放左边 等于arr[R]的放中间 大于arr[R]的放右边
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherland(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }

        //小于基准位置值的右边界
        int less = L - 1;
        int index = L;
        //大于基准位置值的左边界
        int more = R;

        while (index < more) {
            //当前位置和基准位置的值一样 不进行替换 索引前进
            if (arr[index] == arr[R]) {
                index++;
                //索引位置的值小于基准位置的值 这个值需要放左边 所以和less+1位置进行替换 索引继续前进
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
                //索引位置大于基准位置这个值需要放右边 所以和--more位置替换 因为最后一个位置是基准位置只能最后替换 所以和之前位置替换
                // 替换之后index不变 因为需要知道替换过的位置和基准位置继续比较
            } else {
                swap(arr, index, --more);
            }
        }
        //最后索引位置和基准位置替换
        swap(arr, index, R);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int i1) {
        int temp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 5, 2, 8, 3, 2, 6, 9, 9};
        int[] ints = netherland(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
