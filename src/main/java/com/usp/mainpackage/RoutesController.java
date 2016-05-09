/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usp.mainpackage;

import com.googlecode.vkapi.exceptions.VkException;
import com.usp.dbpackage.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.usp.service.CityService;

import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/")
public class RoutesController {



	@RequestMapping(value = "/")
	public String index() {
            return "index";
	}
        @RequestMapping(value = "/home")
	public String home() throws InterruptedException, VkException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//            Main.execute();
            return "home";
	}
        @RequestMapping(value = "/*")
        public String one(Model model) {
            model.addAttribute("ErrorCode", "404");
            return "404";
        }


    @Autowired
    private CityService cityService;


    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(){

        cityService.insert(new City("Rostov", 456));

        City ne = cityService.find(213);
        return "home";
    }
}