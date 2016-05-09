package com.usp.convert;

import com.usp.domain.user.VkCareer;
import com.usp.domain.user.VkUniversity;
import com.usp.domain.user.VkUserSearch;
import org.codehaus.jackson.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by aleksandr on 09.05.16.
 */

final class Convert {

    @SuppressWarnings("all")
    public static VkUserSearch toVkUserSearch(JsonNode node) {

        int vkUserId, groupId, country, city, from, until;
        String firstName, lastName, company, position;
        int id_university, faculty, chair, graduation, uCountry, uCity;
        String name;
        String faculty_name;
        String education_form;
        String education_status;
        String chair_name;
        List<VkCareer> vkCareer;
        List<VkUniversity> vkUniversity;

        JsonNode jsonNode = node.get("career");
        JsonNode jsonNode1 = node.get("universities");

        if (node.get("career") != null && node.get("career").size() > 0 && node.get("universities") != null && node.get("universities").size() > 0) {
            vkUserId = node.findValue("uid").getValueAsInt();
            firstName = node.findValue("first_name").getValueAsText();
            lastName = node.findValue("last_name").getValueAsText();

            Iterator<JsonNode> elements = jsonNode.iterator();
            vkCareer = new ArrayList<>();

            Iterator<JsonNode> elements1 = jsonNode1.iterator();
            vkUniversity = new ArrayList<>();

            while (elements1.hasNext()) {
                JsonNode element = elements1.next();

                id_university = element.get("id") != null ? element.get("id").getValueAsInt() : -1;
                faculty = element.get("faculty") != null ? element.get("faculty").getValueAsInt() : -1;
                uCountry = element.get("country") != null ? element.get("country").getValueAsInt() : -1;
                uCity = element.get("city") != null ? element.get("city").getValueAsInt() : -1;
                name = element.get("name") != null ? element.get("name").getValueAsText() : null;
                faculty_name = element.get("faculty_name") != null ? element.get("faculty_name").getValueAsText() : null;
                chair = element.get("chair") != null ? element.get("chair").getValueAsInt() : -1;
                chair_name = element.get("chair_name") != null ? element.get("chair_name").getValueAsText() : null;
                graduation = element.get("graduation") != null ? element.get("graduation").getValueAsInt() : -1;
                education_form = element.get("education_form") != null ? element.get("education_form").getValueAsText() : null;
                education_status = element.get("education_status") != null ? element.get("education_status").getValueAsText() : null;
                vkUniversity.add(new VkUniversity(id_university, uCountry, uCity, name, faculty, faculty_name, education_form, education_status, chair, chair_name, graduation));
            }

            while (elements.hasNext()) {
                JsonNode element = elements.next();

                groupId = element.get("group_id") != null ? element.get("group_id").getValueAsInt() : -1;
                company = element.get("company") != null ? element.get("company").getValueAsText() : null;
                country = element.get("country_id") != null ? element.get("country_id").getValueAsInt() : -1;
                city = element.get("city_id") != null ? element.get("city_id").getValueAsInt() : -1;
                from = element.get("from") != null ? element.get("from").getValueAsInt() : -1;
                until = element.get("until") != null ? element.get("until").getValueAsInt() : -1;
                position = element.get("position") != null ? element.get("position").getValueAsText() : null;

                vkCareer.add(new VkCareer(groupId, company, country, city, from, until, position));
            }

            return new VkUserSearch(vkUserId, firstName, lastName, vkCareer, vkUniversity);
        }
        return null;
    }
}
