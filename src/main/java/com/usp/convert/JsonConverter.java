package com.usp.convert;

import com.googlecode.vkapi.domain.error.VkErrorResponse;
import com.usp.domain.user.VkUserSearch;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by aleksandr on 09.05.16.
 */
public class JsonConverter extends com.googlecode.vkapi.convert.JsonConverter{

    private com.googlecode.vkapi.convert.JsonConverter jsonConverter;
    private Logger logger = LoggerFactory.getLogger(JsonConverter.class);
    public static final JsonConverter INSTANCE = new JsonConverter();

    public JsonConverter() {
        jsonConverter = com.googlecode.vkapi.convert.JsonConverter.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public List<VkUserSearch> jsonToSearchList(String json) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        logger.debug("jsonToSearchList: processing {}...", json);

        Method m = jsonConverter.getClass().getDeclaredMethod("getRootResponseArray", String.class);
        m.setAccessible(true);
        Iterator<JsonNode> elements = (Iterator<JsonNode>) m.invoke(INSTANCE, new Object[] {json});

        List<VkUserSearch> result = new ArrayList<>();
        while (elements.hasNext()) {
            VkUserSearch user = Convert.toVkUserSearch(elements.next());
            if (user != null) {
                result.add(user);
            }
        }
        return result;
    }

    public VkErrorResponse jsonToVkError(String json, String message) {
        logger.debug("jsonToVkError: processing {}...", json);
        VkErrorResponse vkError = new VkErrorResponse(112, message);
        return vkError;
    }
}
