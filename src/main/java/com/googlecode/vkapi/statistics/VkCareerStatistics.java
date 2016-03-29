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
                for (int j = 0; j < currentUser.getCareer().size(); j++) {
                    city = String.valueOf(currentUser.getCareer().get(j).getCityId());
                    if (city.equals("-1")) {
                        continue;
                    }
                    if (hashMap.containsKey(city)) {
                        hashMap.replace(city, hashMap.get(city) + 1);
                    } else {
                        hashMap.put(city, 1);
                    }
                }
            }
        return hashMap;
    }

    @Override
    public HashMap<String, Integer> employedStats(Collection<VkUserSearch> list) {
        Iterator<VkUserSearch> users = list.iterator();
        HashMap<String, Integer> hashMap = new HashMap<>();
        int from, until;
        int lastCareer;
        int employed = 0;
        int unemployed = 0;
        while (users.hasNext()) {
            VkUserSearch currentUser = users.next();
            lastCareer = currentUser.getCareer().size() - 1;
                until = currentUser.getCareer().get(lastCareer).getUntil();
                from = currentUser.getCareer().get(lastCareer).getFrom();
                if (from != -1) {
                    if (until == -1 || until == 2016) {
                        employed++;
                    }
                }
                else {
                    unemployed++;
                }
        }
        hashMap.put("Employed", employed);
        hashMap.put("Unemployed", unemployed);
        return hashMap;
    }

    @Override
    public HashMap<String, Integer> employmentPeriod(Collection<VkUserSearch> list) {
        Iterator<VkUserSearch> users = list.iterator();
        HashMap<String, Integer> hashMap = new HashMap<>();
        int lastUniversity;
        int graduation, careerStart;
        while (users.hasNext()) {
            VkUserSearch currentUser = users.next();
            lastUniversity = currentUser.getUniversity().size() - 1;
            graduation = currentUser.getUniversity().get(lastUniversity).getGraduation();
            if (graduation == -1) {
                continue;
            }
            for (int j = 0; j < currentUser.getCareer().size(); j++) {
                careerStart = currentUser.getCareer().get(j).getFrom();
                if (careerStart == -1) {
                    continue;
                }
                else {
                    if ((careerStart - graduation) >= -5) {
                        if (hashMap.containsKey(String.valueOf(careerStart - graduation))) {
                            hashMap.replace(String.valueOf(careerStart - graduation), hashMap.get(String.valueOf(careerStart - graduation)) + 1);
                        } else {
                            hashMap.put(String.valueOf((careerStart - graduation)), 1);
                        }
                        break;
                    }
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
