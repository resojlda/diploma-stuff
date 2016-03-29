package com.googlecode.vkapi.convert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.vkapi.domain.user.*;
import org.apache.commons.lang3.Validate;
import org.codehaus.jackson.JsonNode;

import com.googlecode.vkapi.domain.VkOAuthToken;
import com.googlecode.vkapi.domain.error.VkMethodParam;
import com.googlecode.vkapi.domain.group.VkGroup;
import com.googlecode.vkapi.domain.group.VkGroupBuilder;
import com.googlecode.vkapi.domain.message.*;

/**
 * Utility class for converting from {@link JsonNode} instances to vk.com domain
 * objects
 * 
 * @author Alexey Grigorev
 */
final class Convert {

    private Convert() {
    }

    public static VkOAuthToken toAuthToken(JsonNode node) {
        String accessToken = node.get("access_token").getValueAsText();
        int expiresIn = node.get("expires_in").getValueAsInt();
        int userId = node.get("user_id").getValueAsInt();
        return new VkOAuthToken(accessToken, expiresIn, userId);
    }

    public static VkUser toVkUser(JsonNode node) {
        int vkUserId = node.get("uid").getValueAsInt();
        String firstName = node.get("first_name").getValueAsText();
        String lastName = node.get("last_name").getValueAsText();
        String photo = node.get("photo").getValueAsText();

        VkUserBuilder builder = VkUserBuilder.user(vkUserId).addName(firstName, lastName);
        builder.addPhoto(photo);

        JsonNode bdate = node.get("bdate");
        if (bdate != null) {
            // date format: "7.7.1987"
            builder.addBirthday(bdate.getValueAsText());
        }

        return builder.build();
    }

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

    public static VkWallMessage toVkWallMessage(JsonNode node) {
        int messageId = node.get("id").getValueAsInt();
        VkWallMessageBuilder builder = VkWallMessageBuilder.message(messageId);

        int senderId = node.get("from_id").getValueAsInt();
        builder.addSender(senderId);
        int receiverId = node.get("to_id").getValueAsInt();
        builder.addReceiver(receiverId);
        builder.addDate(node.get("date").getLongValue());
        builder.addText(node.get("text").getValueAsText());

        JsonNode signerNode = node.get("signer_id");
        if (signerNode != null) {
            builder.addSigner(VkMessageSender.of(signerNode.getIntValue()));
        }

        addWallMessageAttachments(builder, node);

        return builder.build();
    }

    private static void addWallMessageAttachments(VkWallMessageBuilder builder, JsonNode node) {
        JsonNode attachmentsNode = node.get("attachments");
        if (attachmentsNode == null) {
            return;
        }

        Validate.isTrue(attachmentsNode.isArray(), "attachments is expected to be an array, got %s", attachmentsNode);
        Iterator<JsonNode> nodes = attachmentsNode.iterator();

        while (nodes.hasNext()) {
            JsonNode attachmentNode = nodes.next();
            VkAttachment attachment = toAttachment(attachmentNode);
            // TODO: get rig of null
            if (attachment != null) {
                builder.addAttachment(attachment);
            }
        }
    }

    private static VkAttachment toAttachment(JsonNode attachmentNode) {
        String typeString = attachmentNode.get("type").getValueAsText();
        VkAttachmentType type = VkAttachmentType.valueOf(typeString.toUpperCase());

        switch (type) {
            case LINK:
                return linkAttachment(attachmentNode);

        }
        // TODO: get rig of null
        return null;
    }

    private static VkLinkAttachment linkAttachment(JsonNode attachmentNode) {
        JsonNode linkNode = attachmentNode.get("link");

        String title = linkNode.get("title").getValueAsText();
        String url = linkNode.get("url").getValueAsText();

        return new VkLinkAttachment(title, url);
    }

    public static VkGroup toVkGroup(JsonNode node) {
        int gid = node.get("gid").getValueAsInt();
        VkGroupBuilder builder = VkGroupBuilder.group(gid);

        JsonNode name = node.get("name");
        if (name != null) {
            builder.addGroupName(name.getValueAsText());
        }

        JsonNode screenName = node.get("screen_name");
        if (screenName != null) {
            builder.addScreenName(screenName.getValueAsText());
        }

        JsonNode isClosed = node.get("is_closed");
        if (isClosed != null) {
            builder.setClosed(isClosed.getValueAsInt());
        }

        JsonNode type = node.get("type");
        if (type != null) {
            builder.setGroupType(type.getValueAsText());
        }

        JsonNode photo = node.get("photo");
        if (photo != null) {
            builder.addPhoto(photo.getValueAsText());
        }

        JsonNode photoMedium = node.get("photo_medium");
        if (photoMedium != null) {
            builder.addPhotoMedium(photoMedium.getValueAsText());
        }

        JsonNode photoBig = node.get("photo_big");
        if (photoBig != null) {
            builder.addPhotoBig(photoBig.getValueAsText());
        }

        return builder.build();
    }

    public static VkMethodParam toVkMethodParam(JsonNode node) {
        return new VkMethodParam(node.get("key").getValueAsText(), node.get("value").getValueAsText());
    }

}
