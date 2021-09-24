package com.algorithm.test;

/**
 * @Description: 多数元素
 * @author: zyu
 * @date: 2021年09月03日 14:14
 */
public class Code_MajorityElement {

    public static int majorityElement(int[] nums) {
        int value = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                value = nums[i];
                count++;
            } else {
                if (value == nums[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return value;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1,2, 2,  2, 2};
        System.out.println(majorityElement(arr));
    }
}
