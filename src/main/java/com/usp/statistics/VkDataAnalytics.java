package com.usp.statistics;

import com.googlecode.vkapi.domain.OAuthToken;
import com.googlecode.vkapi.exceptions.VkException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author Aleksandr Stepanenko
 */

public interface VkDataAnalytics {

    HashMap<String, Integer> workplaceStats(int universityId, OAuthToken authToken) throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, VkException;

    HashMap<String, Integer> employedStats(int universityId, OAuthToken authToken) throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, VkException;

    HashMap<String, Integer> employmentPeriod(int universityId, OAuthToken authToken) throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, VkException;

    void print(HashMap<String, Integer> hashMap);

}
