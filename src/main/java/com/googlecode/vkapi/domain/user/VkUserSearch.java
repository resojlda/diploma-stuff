package com.googlecode.vkapi.domain.user;

import java.util.List;

/**
 * @author Aleksandr Stepanenko
 */
public class VkUserSearch {
    private final int vkUserId;
    private final String firstName;
    private final String lastName;
    private final List<VkCareer> career;

    public VkUserSearch(int vkUserId, String firstName, String lastName, List<VkCareer> career) {
        this.vkUserId = vkUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
    }

    public List<VkCareer> getCareer() {
        return career;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getVkUserId() {
        return vkUserId;
    }



}
