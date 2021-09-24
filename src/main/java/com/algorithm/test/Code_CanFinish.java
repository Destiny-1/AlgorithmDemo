package com.algorithm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 课程表 https://leetcode-cn.com/problems/course-schedule/
 * @author: zyu
 * @date: 2021年09月06日 16:03
 */
public class Code_CanFinish {
    List<List<Integer>> list;
    boolean result = true;
    int[] visited;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        list = new ArrayList<>();
        visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            list.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        for (int i = 0; i < numCourses && result; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return result;
    }

    private void dfs(int i) {
        //正在访问
        visited[i] = 1;
        for (int v : list.get(i)) {
            //没有访问过
            if (visited[v] == 0) {
                dfs(v);
                if (!result) {
                    return;
                }
                //正在访问  当一个正在访问 遇到另外一个正在访问 则表示有循环依赖 不成立
            } else if (visited[v] == 1) {
                result = false;
                return;
            }
        }
        //访问完成
        visited[i] = 2;
    }


    public static void main(String[] args) {
        int nums = 3;
        int[][] arr = new int[][]{{1, 0}, {1, 2}, {0, 1}};
        Code_CanFinish code_canFinish = new Code_CanFinish();
        System.out.println(code_canFinish.canFinish(nums, arr));
    }
}
