package com.usp.http;

import com.googlecode.vkapi.domain.OAuthToken;
import com.googlecode.vkapi.domain.error.VkErrorResponse;
import com.googlecode.vkapi.exceptions.VkException;
import com.googlecode.vkapi.exceptions.VkExceptions;
import com.usp.convert.JsonConverter;
import com.usp.domain.user.VkUserSearch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by aleksandr on 09.05.16.
 */
public class HttpVkApi  extends com.googlecode.vkapi.HttpVkApi{

    public static final String[] USER_CAREER = { "uid", "first_name", "last_name", "career", "universities" };  //response career params
    private UriCreator uriCreator = new UriCreator();
    private JsonConverter jsonConverter;

    private HashMap<Integer, Integer> calendar; //calendar data to divide request

    public HttpVkApi(String appId, String appKey, String responseUri) {
        super(appId, appKey, responseUri);
        jsonConverter = JsonConverter.INSTANCE;
    }

    //getting a list of users for the university
    public Collection<VkUserSearch> getUsers(int universityId, OAuthToken authToken) throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, VkException {

        Collection<VkUserSearch> list = new ArrayList<>();
        if (calendar == null) setCalendar();

        String json; //response from vk

        for (int i = 1; i <= calendar.size() ; i++) {
            for (int j = 1; j <= calendar.get(i) ; j++) {

                UriCreator.Params params = UriCreator.Params.create()
                        .add("birth_month", String.valueOf(i))
                        .add("birth_day", String.valueOf(j))
                        .add("count", "1000")
                        .add("com/usp/university", String.valueOf(universityId));

                String uri = uriCreator.userSearchUri(params, USER_CAREER, authToken);

                try {
                    json = executeAndProcess(uri, authToken);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    Thread.sleep(60000);
                    j--;
                    continue;
                }

                System.out.println("i, j = " + i + ", " + j + " " + json);
                list.addAll(jsonConverter.jsonToSearchList(json));

                //Delay. 3 requests per second
                if ((j%3) == 0) {
                    Thread.sleep(1000);
                }
                //Pause to avoid empty response
                if ((j%29) == 0) {
                    Thread.sleep(60000);
                }
            }
        }
        return list;
    }

    private String executeAndProcess(String uri, OAuthToken authToken) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, VkException {
        //invoking private method via reflection
        Method m = getClass().getSuperclass().getDeclaredMethod("executeAndProcess", new Class[]{String.class, OAuthToken.class});
        m.setAccessible(true);
        String json = (String) m.invoke(this, new Object[] {uri, authToken});
        if (!jsonConverter.isArray(json)) {
            VkErrorResponse error = jsonConverter.jsonToVkError(json, "Empty response");
            VkExceptions.throwAppropriate(error, authToken);
        }
        return json;
    }

    private void setCalendar() {
        calendar = new HashMap<>();
        calendar.put(1, 31);
        calendar.put(2, 29);
        calendar.put(3, 31);
        calendar.put(4, 30);
        calendar.put(5, 31);
        calendar.put(6, 30);
        calendar.put(7, 31);
        calendar.put(8, 31);
        calendar.put(9, 30);
        calendar.put(10, 31);
        calendar.put(11, 30);
        calendar.put(12, 31);
    }

}
