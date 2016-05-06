package com.googlecode.vkapi.convert;

import java.util.*;

import com.googlecode.vkapi.domain.user.VkUserSearch;
import org.apache.commons.lang3.Validate;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.vkapi.domain.VkOAuthToken;
import com.googlecode.vkapi.domain.error.VkErrorResponse;
import com.googlecode.vkapi.domain.error.VkMethodParam;
import com.googlecode.vkapi.domain.group.VkGroup;
import com.googlecode.vkapi.domain.message.VkWallMessage;
import com.googlecode.vkapi.domain.user.GroupUsers;
import com.googlecode.vkapi.domain.user.VkUser;
import java.io.IOException;
import university.City;
import university.University;

/**
 * Converts json responses from VK.com
 * 
 * @author Alexey Grigorev
 */
public class JsonConverter {

    /**
     * Shared instance for using by all classes
     */
    public static final JsonConverter INSTANCE = new JsonConverter();

    private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public List<VkUser> jsonToUserList(String json) {
        logger.debug("jsonToUserList: processing {}...", json);

        Iterator<JsonNode> elements = getRootResponseArray(json);

        List<VkUser> result = new ArrayList<VkUser>();
        while (elements.hasNext()) {
            VkUser user = Convert.toVkUser(elements.next());
            result.add(user);
        }

        return result;
    }
    
     public ArrayList<City> getArray(String cityJson){
        ArrayList<City> array = new ArrayList<>();
        Iterator<JsonNode> elements = getRootResponseArray(cityJson);
        JsonNode node;
        while (elements.hasNext()){
            node = elements.next();
            City city = new City(node.get("cid").getValueAsInt(), 
                            node.get("title").getValueAsText(), null);
            array.add(city);
        }
        return array;
    }
     
     public ArrayList<University> getArrayUniv(String json){
        ArrayList<University> array = new ArrayList<>();
        Iterator<JsonNode> elements = getRootResponseArray(json);
        JsonNode node;
        elements.next();
        while (elements.hasNext()){
            node = elements.next();
            University univ = new University(node.get("id").getValueAsInt(),
                                        node.get("title").getValueAsText());
            array.add(univ);
        }
        return array;
    }
   
    public List<VkUserSearch> jsonToSearchList(String json) {
        logger.debug("jsonToSearchList: processing {}...", json);

        Iterator<JsonNode> elements = getRootResponseArray(json);

        List<VkUserSearch> result = new ArrayList<>();
        while (elements.hasNext()) {
            VkUserSearch user = Convert.toVkUserSearch(elements.next());
            if (user != null) {
                result.add(user);
            }
        }

        return result;
    }

    public int getArraySize(String json) {
        JsonNode jsonNode = getRootResponse(json);
        return jsonNode.isArray() ? jsonNode.size() : -1;
    }

    private Iterator<JsonNode> getRootResponseArray(String json) {
        JsonNode jsonNode = getRootResponse(json);
        Validate.isTrue(jsonNode.isArray(), "expected array in response, got: %s", jsonNode);
        return jsonNode.iterator();
    }
    
    private JsonNode getRootResponse(String json) {
        return toJsonNode(json).get("response");
    }
    
    public JsonNode getJson(String json){
        return getRootResponse(json);
    }
    
    public JsonNode toJson(String json) throws IOException{
        return mapper.readTree(json);
    }
    
    private JsonNode toJsonNode(String json) {
        try {
            return mapper.readTree(json);
        } catch (Exception e) {
            throw new IllegalArgumentException("can't parse given json, got: " + json, e);
        }
    }

    public VkOAuthToken jsonToAuthToken(String json) {
        JsonNode root = toJsonNode(json);
        return Convert.toAuthToken(root);
    }

    public Collection<VkWallMessage> jsonToWallMessage(String json) {
        logger.debug("jsonToWallMessage: processing {}...", json);
        Iterator<JsonNode> elements = getRootResponseArray(json);

        List<VkWallMessage> result = new ArrayList<VkWallMessage>();
        while (elements.hasNext()) {
            // it may be 'count' (integer), skipping it
            JsonNode messageNode = elements.next();
            if (!messageNode.isObject()) {
                continue;
            }

            VkWallMessage message = Convert.toVkWallMessage(messageNode);
            result.add(message);
        }

        return result;
    }

    public Set<Integer> jsonToIntegerSet(String json) {
        logger.debug("jsonToIntegerList: processing {}...", json);
        Iterator<JsonNode> elements = getRootResponseArray(json);
        return toIntegers(elements);
    }

    private static Set<Integer> toIntegers(Iterator<JsonNode> elements) {
        Set<Integer> result = new HashSet<Integer>();
        while (elements.hasNext()) {
            JsonNode next = elements.next();
            result.add(next.getValueAsInt());
        }
        return result;
    }

    public List<VkGroup> jsonToVkGroups(String json) {
        logger.debug("jsonToVkGroups: processing {}...", json);
        List<VkGroup> result = new ArrayList<VkGroup>();

        Iterator<JsonNode> elements = getRootResponseArray(json);
        while (elements.hasNext()) {
            VkGroup vkGroup = Convert.toVkGroup(elements.next());
            result.add(vkGroup);
        }

        return result;
    }
    
    public GroupUsers jsonToGroupUsers(String json) {
        JsonNode response = getRootResponse(json);
        
        int totalCount = response.get("count").getValueAsInt();
        JsonNode usersNode = response.get("users");
        Set<Integer> users = toIntegers(usersNode.iterator());
        
        return new GroupUsers(totalCount, users);
    }

    public VkErrorResponse jsonToVkError(String json) {
        logger.debug("jsonToVkError: processing {}...", json);

        JsonNode jsonNode = toJsonNode(json).get("error");

        int errorCode = jsonNode.get("error_code").getValueAsInt();
        String errorMessage = jsonNode.get("error_msg").getValueAsText();

        VkErrorResponse vkError = new VkErrorResponse(errorCode, errorMessage);

        JsonNode params = jsonNode.get("request_params");
        if (params != null && params.isArray()) {
            Iterator<JsonNode> paramsIt = params.iterator();

            while (paramsIt.hasNext()) {
                VkMethodParam param = Convert.toVkMethodParam(paramsIt.next());
                vkError.addMethodParam(param);
            }
        }

        return vkError;
    }

    public VkErrorResponse jsonToVkError(String json, String message) {
        logger.debug("jsonToVkError: processing {}...", json);

        VkErrorResponse vkError = new VkErrorResponse(112, message);
        return vkError;
    }



}
