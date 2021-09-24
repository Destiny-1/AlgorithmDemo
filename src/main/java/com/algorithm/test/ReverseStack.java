package com.algorithm.test;

import java.util.Stack;

/**
 * @Description: 堆的逆序 要求不额外申请数据结构 只用递归
 * @author: zyu
 * @date: 2021年06月10日 17:00
 */
public class ReverseStack {

    /****
     * f的作用每次只拿出最底层的元素
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    /***
     * 返回最后面的一个点
     * 第一次调用拿到栈顶元素 然后栈的元素减一 接着递归调用此方法 知道拿到最后一个元素
     * 当拿到最后一个元素时 其他的元素全部都被递归函数中饿result保留着了 、
     * 然后从最底层往外 一次把元素放进去 最后返回最底层的元素
     * 比如  123
     * result   1           2          3
     * last    f(2,3)      f(3)        3
     * 第一次先拿出1 然后栈变成只有2，3两个元素 然后调用f(2,3) 拿出2 然后调用f(3)
     * 因为3是最后一个元素 所以把3当做结果返回 下次reverse方法 调用的栈就只包含1，2了
     * @param stack
     * @return
     */
    private static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.empty()) {
            return result;
        } else {
            //拿到最后一个元素
            int last = f(stack);
            //把最后一个元素之外的其他元素放回去
            stack.push(result);
            return  last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> _stack = new Stack<Integer>();
        _stack.add(1);
        _stack.add(2);
        _stack.add(3);
        reverse(_stack);
        while (!_stack.empty()) {
            System.out.println(_stack.pop());
        }
    }
}
