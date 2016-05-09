package com.usp.domain.user;

import java.util.List;

/**
 * Created by aleksandr on 09.05.16.
 */
public class VkUserSearch {
    private final int vkUserId;
    private final String firstName;
    private final String lastName;
    private final List<VkCareer> career;
    private final List<VkUniversity> university;

    public VkUserSearch(int vkUserId, String firstName, String lastName, List<VkCareer> career, List<VkUniversity> university) {
        this.vkUserId = vkUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.university = university;
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

    public List<VkUniversity> getUniversity() {
        return university;
    }

}
