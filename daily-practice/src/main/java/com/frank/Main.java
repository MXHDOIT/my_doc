package com.frank;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: maxinhang.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        testMerge();
    }

    private static void testMerge() {
        IncrementalMerge merger = new IncrementalMerge();

        // 准备测试数据（使用传统初始化方式）
        Map<Long, Map<Long, Set<Long>>> inIds = new HashMap<>();

        Map<Long, Set<Long>> customer1Data = new HashMap<>();
        customer1Data.put(101L, new HashSet<>(Arrays.asList(1001L, 1002L)));
        customer1Data.put(102L, new HashSet<>(Arrays.asList(1003L, 1004L)));
        inIds.put(1L, customer1Data);

        Map<Long, Set<Long>> customer2Data = new HashMap<>();
        customer2Data.put(201L, new HashSet<>(Arrays.asList(2001L, 2002L)));
        inIds.put(2L, customer2Data);

        // 合并数据
        merger.mergeInIds(inIds);

        // 准备要移除的数据
        Map<Integer, Set<Long>> outIds = new HashMap<>();
        outIds.put(2, new HashSet<>(Collections.singletonList(102L))); // 移除计划ID=102
        outIds.put(3, new HashSet<>(Collections.singletonList(2001L))); // 移除创意ID=2001

        // 合并移除项并获取被移除的创意
        Set<Long> removedCreatives = merger.mergeOutIdsAndGetRemovedCreatives(outIds);

        System.out.println("被移除的创意ID: " + removedCreatives);
        // 输出: 被移除的创意ID: [1003, 1004, 2001]

        merger.mergeOutIds(outIds);

        // 获取最终结果
        Pair<Map<Long, Map<Long, Set<Long>>>, Map<Integer, Set<Long>>> results = merger.getMergeResult();

        System.out.println("剩余有效数据: " + results.getKey());
        // 输出: 剩余有效数据: {1={101=[1001, 1002]}, 2={201=[2002]}}
        System.out.println("合并淘汰数据: " + results.getValue());
    }
}