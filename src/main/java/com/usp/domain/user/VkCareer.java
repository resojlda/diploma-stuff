package com.usp.domain.user;

/**
 * Created by aleksandr on 09.05.16.
 */
public class VkCareer {

    private final int groupId;
    private final String company;
    private final int countryId;
    private final int cityId;
    private final int from;
    private final int until;
    private final String position;

    public VkCareer(int groupId, String company, int countryId, int cityId, int from, int until, String position) {
        this.groupId = groupId;

        this.company = company;
        this.countryId = countryId;
        this.cityId = cityId;
        this.from = from;
        this.until = until;
        this.position = position;
    }


    public String getCompany() {
        return company;
    }

    public int getCountryId() {
        return countryId;
    }

    public int getCityId() {
        return cityId;
    }

    public int getFrom() {
        return from;
    }

    public int getUntil() {
        return until;
    }

    public String getPosition() {
        return position;
    }

    public int getGroupId() {
        return groupId;
    }

}
