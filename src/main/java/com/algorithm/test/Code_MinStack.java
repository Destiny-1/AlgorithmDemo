package com.algorithm.test;

import java.util.*;

/**
 * @Description: 最小栈 https://leetcode-cn.com/problems/min-stack/
 * @author: zyu
 * @date: 2021年09月02日 15:48
 */
public class Code_MinStack {
    static class MinStack {
        Deque<Integer> stack;
        Deque<Integer> minQue;

        public MinStack() {
            stack = new LinkedList<>();
            minQue = new LinkedList<>();
            minQue.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            minQue.push(Math.min(val, minQue.peek()));
        }

        public void pop() {
            stack.pop();
            minQue.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minQue.peek();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());

    }
}
