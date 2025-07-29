package com.frank;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据合并与过滤工具类
 * 功能：
 * 1. 合并账户-计划-创意的层级关系
 * 2. 支持按类型过滤无效ID
 * 3. 生成最终有效的层级关系和待移除ID集合
 */
public class IncrementalMerge {
    // 账户ID到计划ID集合的映射
    private final Map<Long, Set<Long>> customerToCampaigns = new HashMap<>();

    // 计划ID到创意ID集合的映射
    private final Map<Long, Set<Long>> campaignToCreatives = new HashMap<>();

    // 所有有效的创意ID集合
    private final Set<Long> activeCreativeIds = new HashSet<>();

    // 按类型分类的待移除ID集合 (1=账户, 2=计划, 3=创意)
    private final Map<Integer, Set<Long>> idsToRemove = new HashMap<>();

    /**
     * 合并进入投放数据
     * @param inIds 输入的数据结构 Map<账户ID, Map<计划ID, Set<创意ID>>>
     */
    public void mergeInIds(Map<Long, Map<Long, Set<Long>>> inIds) {
        if (inIds == null) return;
        inIds.forEach((customerId, campaignMap) -> {
            // 合并账户到广告计划的映射
            Set<Long> campaigns = customerToCampaigns
                    .computeIfAbsent(customerId, k -> new HashSet<>());
            campaigns.addAll(campaignMap.keySet());

            // 合并广告计划到创意的映射
            campaignMap.forEach((campaignId, creativeIds) -> {
                Set<Long> creatives = campaignToCreatives
                        .computeIfAbsent(campaignId, k -> new HashSet<>());
                creatives.addAll(creativeIds);
                activeCreativeIds.addAll(creativeIds);
            });
        });
    }

    /**
     * 合并需要移除在投的ID
     * @param outIds 待移除的ID集合 Map<类型, Set<ID>>
     */
    public void mergeOutIds(Map<Integer, Set<Long>> outIds) {
        if (outIds == null) return;
        outIds.forEach((type, ids) -> {
            // 合并到待移除集合
            Set<Long> removalSet = idsToRemove
                    .computeIfAbsent(type, k -> new HashSet<>());
            removalSet.addAll(ids);

            // 立即从相应集合中移除
            // 从相应集合中移除
            if (type == 1) {
                customerToCampaigns.keySet().removeAll(ids); // 移除账户
            } else if (type == 2) {
                campaignToCreatives.keySet().removeAll(ids); // 移除计划
            } else if (type == 3) {
                activeCreativeIds.removeAll(ids); // 移除创意
            }
        });
    }

    /**
     * 合并需要移除在投的ID并返回被移除的在投创意ID集合
     * @param outIds 待移除的ID集合 Map<类型, Set<ID>>
     * @return 被移除的创意ID集合
     */
    public Set<Long> mergeOutIdsAndGetRemovedCreatives(Map<Integer, Set<Long>> outIds) {
        Set<Long> removedCreativeIds = new HashSet<>();

        if (outIds == null) return removedCreativeIds;

        for (Map.Entry<Integer, Set<Long>> entry : outIds.entrySet()) {
            Integer type = entry.getKey();
            Set<Long> ids = entry.getValue();

            // 合并到待移除集合
            Set<Long> removalSet = idsToRemove
                    .computeIfAbsent(type, k -> new HashSet<>());
            removalSet.addAll(ids);

            // 从相应集合中移除并收集被移除的创意ID
            if (type == 1) {
                // 移除账户时，需要找到这些账户关联的所有创意
                ids.forEach(customerId -> {
                    Set<Long> campaigns = customerToCampaigns.get(customerId);
                    if (campaigns != null) {
                        campaigns.forEach(campaignId -> {
                            Set<Long> creatives = campaignToCreatives.get(campaignId);
                            if (creatives != null) {
                                removedCreativeIds.addAll(creatives);
                            }
                        });
                    }
                });
                customerToCampaigns.keySet().removeAll(ids);
            } else if (type == 2) {
                // 移除计划时，直接收集这些计划的创意
                ids.forEach(campaignId -> {
                    Set<Long> creatives = campaignToCreatives.get(campaignId);
                    if (creatives != null) {
                        removedCreativeIds.addAll(creatives);
                    }
                });
                campaignToCreatives.keySet().removeAll(ids);
            } else if (type == 3) {
                // 直接移除创意
                removedCreativeIds.addAll(ids);
                activeCreativeIds.removeAll(ids);
            }
        }

        return removedCreativeIds;
    }

    /**
     * 获取合并后的结果
     * @return Pair包含:
     *          - 有效的层级数据 Map<账户ID, Map<广告计划ID, Set<创意ID>>>
     *          - 待移除的ID集合 Map<类型, Set<ID>>
     */
    public Pair<Map<Long, Map<Long, Set<Long>>>, Map<Integer, Set<Long>>> getMergeResult() {
        Map<Long, Map<Long, Set<Long>>> result = new HashMap<>();

        customerToCampaigns.forEach((customerId, campaignIds) -> {
            Map<Long, Set<Long>> campaignMap = new HashMap<>();

            campaignIds.stream()
                    .filter(campaignToCreatives::containsKey) // 只保留存在的广告计划
                    .forEach(campaignId -> {
                        Set<Long> validCreatives = campaignToCreatives.get(campaignId)
                                .stream()
                                .filter(activeCreativeIds::contains) // 只保留有效的创意
                                .collect(Collectors.toSet());

                        if (!validCreatives.isEmpty()) {
                            campaignMap.put(campaignId, validCreatives);
                        }
                    });

            if (!campaignMap.isEmpty()) {
                result.put(customerId, campaignMap);
            }
        });

        return new Pair<>(result, idsToRemove);
    }
}
