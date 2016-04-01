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
        Collection<VkUserSearch> list = api.getUsers(5439, new VkOAuthToken("51d231a5763ebc55ec7c6459ba5ce1d34a11d4045525a6aa2fdb2d2aef78722997d73a02b559842aa47ba", 86400, 50306394));
        VkDataAnalytics data = new VkCareerStatistics();
        //HashMap<String, Integer> result = data.workplaceStats(list);
        //HashMap<String, Integer> result = data.employedStats(list);
        HashMap<String, Integer> result = data.employmentPeriod(list);
        data.print(result);
    }
}
