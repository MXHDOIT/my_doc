package leetcode;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author: maxinhang.
 */
public class Solution_ByteDance_2 {

    public static void main(String[] args) {
        Solution_ByteDance_2 solutionByteDance2 = new Solution_ByteDance_2();
        int[][] dependOns = new int[][]{{0, 1}, {1, 2}, {2, 1}};
        boolean dependOn = solutionByteDance2.dependOn(dependOns);
        System.out.println(dependOn);
    }

    // 如果存在循环依赖返回空列表，否则返回拓扑排序结果
    public List<Integer> haveCircularDependency(int n, int[][] prerequisites) {
        // 邻接表存储图结构
        List<List<Integer>> graph = new ArrayList<>();
        // 每个节点的入度
        int[] inDegree = new int[n];

        // 初始化邻接表
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 构建图并计算入度
        for (int[] pre : prerequisites) {
            int a = pre[0];
            int b = pre[1];
            graph.get(a).add(b);  // a -> b 的边
            inDegree[b]++;
        }

        // 存储拓扑排序结果
        List<Integer> result = new ArrayList<>();
        // 队列用于处理入度为0的节点
        Queue<Integer> queue = new LinkedList<>();

        // 入度为0的节点入队
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 拓扑排序
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            // 处理当前节点的所有邻接节点
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                // 若入度变为0，加入队列
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // 如果结果包含所有节点，说明无循环依赖，返回排序结果
        // 否则存在循环依赖，返回空列表
        return result.size() == n ? result : new ArrayList<>();
    }

    public boolean dependOn(int[][] dependOns) {
        Map<Integer, List<Integer>> dependOnMap = new HashMap<>();
        for (int[] dependOn : dependOns) {
            int depend = dependOn[0];
            int cur = dependOn[1];
            dependOnMap.computeIfAbsent(cur, k -> new ArrayList<>()).add(depend);
        }
        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (Integer key : dependOnMap.keySet()) {
            if (!dfs(dependOnMap, key, visiting, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> dependOnMap, Integer key,
                        Set<Integer> visiting, Set<Integer> visited) {
        if (visiting.contains(key)) {
            return false;
        }
        if (visited.contains(key)) {
            return true;
        }
        visiting.add(key);
        List<Integer> depends = dependOnMap.get(key);
        if (CollectionUtil.isNotEmpty(depends)) {
            for (Integer depend : depends) {
                if (!dfs(dependOnMap, depend, visiting, visited)) {
                    return false;
                }
            }
        }
        visiting.remove(key);
        visited.add(key);
        return true;
    }
}
