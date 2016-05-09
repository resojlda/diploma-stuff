package com.usp.statistics;

import com.googlecode.vkapi.domain.VkOAuthToken;
import com.usp.domain.user.VkUserSearch;
import com.usp.http.HttpVkApi;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by aleksandr on 09.05.16.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HttpVkApi api = new HttpVkApi("5301779", "ofLUbv8BcQbLAPgBaGG3", "https://oauth.vk.com/blank.html");
//        Collection<VkUserSearch> list = api.getUsers(5439, new VkOAuthToken("aa26341e03ccbf4709deab1c0e2f912987fc96b7d962cb12d2f559ce3e48e3df5a25da4edd1da7de6ef74", 86400, 50306394));
//        VkDataAnalytics data = new VkCareerStatistics();
        //HashMap<String, Integer> result = data.workplaceStats(list);
        //HashMap<String, Integer> result = data.employedStats(list);
//        HashMap<String, Integer> result = data.employmentPeriod(5439, new VkOAuthToken("aa26341e03ccbf4709deab1c0e2f912987fc96b7d962cb12d2f559ce3e48e3df5a25da4edd1da7de6ef74", 86400, 50306394));
//        data.print(result);
        VkDataAnalytics st = new VkCareerStatistics(api);
        HashMap<String, Integer> hashMap = st.employedStats(5439, new VkOAuthToken("aa26341e03ccbf4709deab1c0e2f912987fc96b7d962cb12d2f559ce3e48e3df5a25da4edd1da7de6ef74", 86400, 50306394));
        st.print(hashMap);
    }
    public static void execute() throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HttpVkApi api = new HttpVkApi("5301779", "ofLUbv8BcQbLAPgBaGG3", "https://oauth.vk.com/blank.html");
        VkDataAnalytics st = new VkCareerStatistics(api);
        HashMap<String, Integer> hashMap = st.employedStats(5439, new VkOAuthToken("aa26341e03ccbf4709deab1c0e2f912987fc96b7d962cb12d2f559ce3e48e3df5a25da4edd1da7de6ef74", 86400, 50306394));
        st.print(hashMap);
    }
}
