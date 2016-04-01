package com.googlecode.vkapi.statistics;

import com.googlecode.vkapi.domain.user.VkUserSearch;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Aleksandr Stepanenko
 */

public interface VkDataAnalytics {

    HashMap<String, Integer> workplaceStats(Collection<VkUserSearch> list);

    HashMap<String, Integer> employedStats(Collection<VkUserSearch> list);

    HashMap<String, Integer> employmentPeriod(Collection<VkUserSearch> list);

    void print(HashMap<String, Integer> hashMap);

}
