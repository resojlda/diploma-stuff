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
        public String stats() throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            HttpVkApi api = new HttpVkApi("5301779", "ofLUbv8BcQbLAPgBaGG3", "https://oauth.vk.com/blank.html");
            VkDataAnalytics st = new VkCareerStatistics(api);
            HashMap<String, Integer> hashMap = st.employedStats(5439, new VkOAuthToken("aa26341e03ccbf4709deab1c0e2f912987fc96b7d962cb12d2f559ce3e48e3df5a25da4edd1da7de6ef74", 86400, 50306394));
            st.print(hashMap);
        return "stats";
    }
    }
