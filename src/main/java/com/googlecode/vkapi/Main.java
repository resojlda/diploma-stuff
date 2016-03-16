package com.googlecode.vkapi;

import com.googlecode.vkapi.domain.VkOAuthToken;
import com.googlecode.vkapi.domain.user.VkUserSearch;
import com.googlecode.vkapi.exceptions.VkException;
import com.googlecode.vkapi.statistics.VkCareerStatistics;
import com.googlecode.vkapi.statistics.VkDataAnalytics;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Aleksandr Stepanenko
 */

public class Main {
    public static void execute() throws InterruptedException, VkException {
        HttpVkApi api = new HttpVkApi("5301779", "ofLUbv8BcQbLAPgBaGG3", "https://oauth.vk.com/blank.html");
        Collection<VkUserSearch> list = api.getCareer(5439, new VkOAuthToken("8574201fa4bd2c57bddc15e64ab920ffb3ae144ae9b5b839dc9afd58d56bd4292d72c4142237331aa6956", 86400, 50306394));
        VkDataAnalytics data = new VkCareerStatistics();
        HashMap<String, Integer> result = data.workplaceStats(list);
        data.print(result);
    }
}
