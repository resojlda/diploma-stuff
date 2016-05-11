package com.usp.mainpackage;

import com.googlecode.vkapi.VkApi;
import com.googlecode.vkapi.domain.OAuthToken;
import com.googlecode.vkapi.domain.VkOAuthToken;
import com.googlecode.vkapi.exceptions.VkException;
import com.googlecode.vkapi.exceptions.VkTokenExpiredException;
import com.usp.http.HttpVkApi;
import com.usp.statistics.VkCareerStatistics;
import com.usp.statistics.VkDataAnalytics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.HashMap;

/**
 * Created by aleksandr on 09.05.16.
 */
    @Controller
    public class VkController {
        private final VkApi vkApi;

        @Autowired
        public VkController(VkApi vkApi) {
            this.vkApi = vkApi;
        }

        @RequestMapping(value = "/vk/auth", method = RequestMethod.GET)
        public String auth() {
            return "redirect:" + vkApi.getAuthUri();
        }

        @RequestMapping(value = "/vk/process", method = RequestMethod.GET)
        public String process(@RequestParam(value = "code", required = false) String code)
                throws VkException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            if (code != null) {
                OAuthToken token = vkApi.authUser(code);

            }
            return "home";
        }

        @ExceptionHandler(VkTokenExpiredException.class)
        public ModelAndView handleVkTokenExpiredException(Principal principal,
                                                          VkTokenExpiredException ex) {

            return new ModelAndView("View name");
        }

        @RequestMapping(value = "/stats", method = RequestMethod.GET)
        public String stats() throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, VkException {
            HttpVkApi api = new HttpVkApi("5301779", "ofLUbv8BcQbLAPgBaGG3", "https://oauth.vk.com/blank.html");
            VkDataAnalytics st = new VkCareerStatistics(api);
            HashMap<String, Integer> hashMap = st.employedStats(5439, new VkOAuthToken("a1f89b71a3210c9f00204148d21be5db65652bfbd85eab1bb4ea18fcb9f3529b5c5cd2b3bd3c488636632", 86400, 50306394));
            st.print(hashMap);
        return "stats";
    }
    }
