package com.googlecode.vkapi.statistics;

import com.googlecode.vkapi.domain.user.VkUserSearch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Aleksandr Stepanenko
 */
public class VkCareerStatistics implements VkDataAnalytics {

    @Override
    public HashMap<String, Integer> workplaceStats(Collection<VkUserSearch> list) {
        Iterator<VkUserSearch> users = list.iterator();
        HashMap<String, Integer> hashMap = new HashMap<>();
        String city;
        while (users.hasNext()) {
            VkUserSearch currentUser = users.next();

            for (int j = 0; j < 2; j++) {
                city = String.valueOf(currentUser.getCareer().get(j).getCityId());
                if (city.equals("-1")) {
                    continue;
                }
                if (hashMap.containsKey(city)){
                    hashMap.replace(city, hashMap.get(city) + 1);
                }
                else {
                    hashMap.put(city, 1);
                }
            }
        }
        return hashMap;
    }

    @Override
    public void print(HashMap<String, Integer> hashMap) {
        if (hashMap.isEmpty()) {
            System.out.println("Ups...something went wrong. Try to receive data again");
            return;
        }

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            System.out.format("%15s %s \n", key, value);
        }
    }


}
